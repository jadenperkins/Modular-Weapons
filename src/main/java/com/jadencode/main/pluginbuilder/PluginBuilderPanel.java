package com.jadencode.main.pluginbuilder;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.jadencode.main.content.loaders.ContentManager;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.items.Item;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.pluginbuilder.modules.ModuleColorCreator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.reflections.Reflections;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PluginBuilderPanel extends JPanel {
    private final JList<Module<? extends Item>> contentModules;
    private final JList<String> currentContentObjects;
    private final JButton exportPlugin;
    private final JTextField pluginName;
    private final JButton importPlugin;
    private final JScrollPane scrollingContentPane;

    private final HashMap<String, Module<? extends Item>> moduleMap = new HashMap<>();

    private ContentEditor editor;

    public PluginBuilderPanel() {
        this.setLayout(null);
        Set<Class<? extends Module>> moduleClasses = new Reflections("com.jadencode.main.pluginbuilder.modules").getSubTypesOf(Module.class);

        List<Module<? extends Item>> modules = new ArrayList<>();
        for (Class<? extends Module> moduleClass : moduleClasses) {
            try {
                Module<? extends Item> module = moduleClass.getConstructor(PluginBuilderPanel.class).newInstance(this);
                modules.add(module);
                this.moduleMap.put(module.getName(), module);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modules.sort((a, b) -> a.getName().compareTo(b.getName()));

        this.contentModules = this.create(new JList<>(modules.toArray(new Module[0])), 10, 60, 200, modules.size() * 18);
        this.currentContentObjects = new JList<>();
        this.scrollingContentPane = this.create(new JScrollPane(this.currentContentObjects), 220, 60, 200, 18);

        this.exportPlugin = this.create(new JButton("Export Plugin"), 10, modules.size() * 20 + 70, 200, 40);
        this.pluginName = this.create(new JTextField(), 10, 10, 200, 18);
        this.importPlugin = this.create(new JButton("Import Plugin"), 10, modules.size() * 20 + 120, 200, 40);



        this.contentModules.setSelectedValue(modules.get(0), true);
        this.updateCurrentObjects(null);

        this.contentModules.addListSelectionListener(e -> this.updateCurrentObjects(null));
        this.currentContentObjects.addListSelectionListener(e -> this.populateSelected());

        this.exportPlugin.addActionListener(e -> this.export(modules));
        this.importPlugin.addActionListener(e -> this.importFile(modules));
    }
    public void populateSelected() {
        String selected = this.getSelectedItem();
        if(this.getSelectedModule().getItemKeys().contains(selected)) {
            Item item = this.getSelectedModule().getItems().get(selected);
            this.editor.setName(item.getName());
            this.editor.populate(item);
        } else {
            this.editor.setName("");
            this.editor.populate(this.editor.getDefault());
        }
        this.repaint();
    }
    public Module<? extends Item> getModule(String name) {
        return this.moduleMap.get(name);
    }
    public void updateCurrentObjects(String name) {
        for (Component component : this.getComponents()) {
            if(component == this.editor) {
                this.remove(this.editor);
                break;
            }
        }
        this.editor = this.getSelectedModule().getContentEditor();
        this.add(this.editor);
        this.editor.onOpened(this.getSelectedModule(), this);

        List<String> strings = this.getSelectedModule().getItemKeys();
        String[] objects = strings.toArray(new String[0]);
        this.currentContentObjects.setListData(objects);
        this.scrollingContentPane.setSize(200, Math.min(600, 18 * Math.max(1, objects.length)));
//        this.currentContentObjects.setSize(200, 18 * Math.max(1, objects.length));
        if(this.getSelectedModule().getItemKeys().contains(name)) {
            this.currentContentObjects.setSelectedValue(name, true);
        } else {
            this.currentContentObjects.setSelectedIndex(0);
        }

        this.populateSelected();
        this.repaint();
    }
    public String getSelectedItem() {
        return this.currentContentObjects.getSelectedValue();
    }
    public Module<? extends Item> getSelectedModule() {
        return this.contentModules.getSelectedValue();
    }
    private <T extends JComponent> T create(T component, int x, int y, int w, int h) {
        component.setLocation(x, y);
        component.setSize(w, h);
        this.add(component);
        return component;
    }
    private void export(List<Module<? extends Item>> modules) {
        String fileName = this.pluginName.getText();
        File dir = new File("plugins");
        File source = new File("plugins/source");
        source.mkdirs();
        try {
            JsonObject json = new JsonObject();
            for (Module<? extends Item> module : modules) {
                JsonArray array = new JsonArray();
                for(String name : module.getItemKeys()) {
                    JsonObject obj = new JsonObject();
                    obj.add("name", new JsonPrimitive(name));
                    module.getItem(name).toJson(obj);
                    array.add(obj);
                }
                json.add(module.getName(), array);
            }
            File sourceOutput = new File(source, fileName + ".json");
            sourceOutput.createNewFile();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(sourceOutput);
            gson.toJson(json, writer);
            writer.close();

            File pluginOutput = new File(dir, fileName + ".plugin");
            pluginOutput.createNewFile();

            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(pluginOutput))));
            dataOutputStream.writeUTF(json.toString());
            dataOutputStream.close();

            System.out.println("Exported files");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    private void importFile(List<Module<? extends Item>> modules) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("plugins"));
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".json") || f.getName().endsWith(".plugin");
            }
            @Override
            public String getDescription() {
                return "Plugin Files";
            }
        });

        chooser.showDialog(null, "Select a Plugin");

        if(chooser.getSelectedFile() != null) {
            File pluginFile = chooser.getSelectedFile();
            this.pluginName.setText(pluginFile.getName().replace(".json", "").replace(".plugin", ""));
            JsonObject pluginObject = this.load(pluginFile);

            for(Module<? extends Item> module : modules) {
                String name = module.getName();
                if(pluginObject.has(name)) {
                    JsonArray array = pluginObject.get(name).getAsJsonArray();
                    for (JsonElement jsonElement : array) {
                        JsonObject content = jsonElement.getAsJsonObject();
                        module.addItem(content.get("name").getAsString(), content);
                    }
                }
            }
        }
        this.updateCurrentObjects(null);
    }
    private JsonObject load(File file) {
        try {
            if(file.getName().endsWith(".plugin")) {
                DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
                String s = datainputstream.readUTF();
                datainputstream.close();

                return new JsonParser().parse(s).getAsJsonObject();
            } else {
                return new JsonParser().parse(new FileReader(file)).getAsJsonObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}