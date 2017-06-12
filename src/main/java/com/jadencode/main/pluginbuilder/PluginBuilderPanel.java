package com.jadencode.main.pluginbuilder;

import com.google.gson.*;
import com.jadencode.main.content.Plugin;
import com.jadencode.main.pluginbuilder.content.ContentObject;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;
import org.reflections.Reflections;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PluginBuilderPanel extends JPanel {
    private final JList<Module<? extends ContentObject>> contentModules;
    private final JList<String> currentContentObjects;
    private final JButton exportPlugin;
    private final JTextField activeFile;
    private final JButton importPlugin;
    private final JScrollPane scrollingContentPane;
    private final HashMap<String, Module<? extends ContentObject>> moduleMap = new HashMap<>();
    private List<String> loadedFiles = new ArrayList<>();
    private ContentEditor editor;

    public PluginBuilderPanel() {
        this.setLayout(null);
        Set<Class<? extends ContentEditor>> contentEditorClasses = new Reflections("com.jadencode.main.pluginbuilder.contenteditors").getSubTypesOf(ContentEditor.class);

        List<Module<? extends ContentObject>> modules = new ArrayList<>();
        for (Class<? extends ContentEditor> contentEditorClass : contentEditorClasses) {
            try {
                ContentEditor<? extends ContentObject> editor = contentEditorClass.getConstructor(PluginBuilderPanel.class).newInstance(this);
                Module<? extends ContentObject> module = new Module<>(editor);
                modules.add(module);
                this.moduleMap.put(module.getName(), module);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        modules.sort(Comparator.comparing(Module::getName));

        GuiHelper helper = GuiHelper.above(this);
        this.activeFile = helper.add(new JTextField(), "Active Plugin", 10, 10, 200, 18);
        this.contentModules = this.create(new JList<>(modules.toArray(new Module[0])), "Content Modules", 10, 60, 200, modules.size() * 18);
        this.currentContentObjects = new JList<>();
        this.scrollingContentPane = this.create(new JScrollPane(this.currentContentObjects), "Module Items", 220, 60, 200, 18);

        this.exportPlugin = this.create(new JButton("Export Plugin"), 10, modules.size() * 20 + 70, 200, 40);
//        this.activeFile = this.create(new JTextField(), "Active Plugin", 10, 10, 200, 18);
        this.importPlugin = this.create(new JButton("Import Plugin"), 10, modules.size() * 20 + 120, 200, 40);

        this.contentModules.setSelectedValue(modules.get(0), true);
        this.updateCurrentObjects(null);

        this.contentModules.addListSelectionListener(e -> this.updateCurrentObjects(null));
        this.currentContentObjects.addListSelectionListener(e -> this.populateSelected());

        this.exportPlugin.addActionListener(e -> this.export(modules));
        this.importPlugin.addActionListener(e -> this.importFiles(modules));
    }

    public String getActivePlugin() {
        return this.activeFile.getText();
    }

    public void populateSelected() {
        String selected = this.getSelectedItem();
        if (this.getSelectedModule().getItemKeys().contains(selected)) {
            ContentObject item = this.getSelectedModule().getItems().get(selected);
            this.editor.setName(item.getName());
            this.editor.populate(item);
        } else {
            this.editor.setName("");
            this.editor.populate(this.editor.getDefault());
        }
        this.repaint();
    }

    public Module<? extends ContentObject> getModule(String name) {
        return this.moduleMap.get(name);
    }

    public void updateCurrentObjects(String name) {
        if (this.getSelectedModule().getContentEditor() != this.editor) {
            if (this.editor != null) {
                this.remove(this.editor);
            }
            this.editor = this.getSelectedModule().getContentEditor();
            this.add(this.editor);
            this.editor.onOpened(this.getSelectedModule(), this);
        }

        List<String> strings = this.getSelectedModule().getItemKeys();
        String[] objects = strings.toArray(new String[0]);
        this.currentContentObjects.setListData(objects);
        this.scrollingContentPane.setSize(200, Math.min(600, 18 * Math.max(1, objects.length)));
//        this.currentContentObjects.setSize(200, 18 * Math.max(1, objects.length));
        if (this.getSelectedModule().getItemKeys().contains(name)) {
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

    public Module<? extends ContentObject> getSelectedModule() {
        return this.contentModules.getSelectedValue();
    }

    private <T extends JComponent> T create(T component, int x, int y, int w, int h) {
        component.setLocation(x, y);
        component.setSize(w, h);
        this.add(component);
        return component;
    }

    private <T extends JComponent> T create(T component, String title, int x, int y, int w, int h) {
        JLabel label = new JLabel(title);
        label.setLocation(x, y);
        label.setSize(w, 18);
        this.add(label);
        return this.create(component, x, y + 20, w, h);
    }

    private void export(List<Module<? extends ContentObject>> modules) {
        if (this.getActivePlugin() == null || this.getActivePlugin().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a name for the file!");
            return;
        }
        String fileName = this.getActivePlugin();
        File dir = new File("plugins");
        File source = new File("plugins/source");
        source.mkdirs();
        try {
            JsonObject json = new JsonObject();
            for (Module<? extends ContentObject> module : modules) {
                JsonArray array = new JsonArray();
                for (String name : module.getItemKeys()) {
                    if (module.getItem(name).getOwner() != null && module.getItem(name).getOwner().equals(fileName)) {
                        JsonObject obj = new JsonObject();
                        obj.add("name", new JsonPrimitive(name));
                        module.getItem(name).toJson(obj);
                        array.add(obj);
                    }
                }
                json.add(module.getName(), array);
            }
            List<String> dependencies = new ArrayList<>(this.loadedFiles);
            dependencies.remove(this.getActivePlugin());
            JsonArray array = JsonHelper.toArray(dependencies);
            json.add("Dependencies", array);

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

            JOptionPane.showMessageDialog(null, "Source and Plugin files have been exported!");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void importFiles(List<Module<? extends ContentObject>> modules) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("plugins"));
        chooser.setMultiSelectionEnabled(true);
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

        chooser.showDialog(null, "Select Plugins");

        if (chooser.getSelectedFiles().length > 0) {
            File[] pluginFiles = chooser.getSelectedFiles();
            this.loadedFiles = Arrays.asList(pluginFiles).stream().map(file -> file.getName().replace(".json", "").replace(".plugin", "")).collect(Collectors.toList());
            List<Plugin> plugins = Arrays.asList(pluginFiles).stream().map(Plugin::new).collect(Collectors.toList());
            plugins.sort(null);

            for (Module<? extends ContentObject> module : modules) {
                String moduleName = module.getName();
                for (Plugin plugin : plugins) {
                    JsonArray array = new JsonHelper(plugin.getPluginObject()).getArray(moduleName);
                    for (JsonElement jsonElement : array) {
                        JsonObject content = jsonElement.getAsJsonObject();
                        module.addItem(content.get("name").getAsString(), content, plugin.getPluginName());
                    }
                }
            }
        }
        this.updateCurrentObjects(null);
    }
}