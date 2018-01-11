package com.main.plugins.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.main.plugins.Plugin;
import com.main.plugins.modules.Module;
import com.main.plugins.pipeline.PipelineObject;
import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PluginBuilderPanel extends JPanel {
    private final JList<ContentEditor<? extends PipelineObject>> contentEditors;
    private final JList<String> currentContentObjects;
    private final JButton exportPlugin;
    private final JTextField activeFile;
    private final JButton importPlugin;
    private final JScrollPane scrollingContentPane;
    private final HashMap<String, Plugin> loadedPlugins = new HashMap<>();
    private final HashMap<String, Plugin> objectOwners = new HashMap<>();

    private final HashMap<String, Module<? extends PipelineObject>> moduleMap = new HashMap<>();

    private ContentEditor editor;

    public PluginBuilderPanel() {
        this.setLayout(null);
        Set<Class<? extends Module>> moduleClasses = new Reflections("com.main.plugins.modules").getSubTypesOf(Module.class);

        List<Module<? extends PipelineObject>> modules = new ArrayList<>();
        for (Class<? extends Module> moduleClass : moduleClasses) {
            try {
                Module<PipelineObject> module = moduleClass.getConstructor().newInstance();
                modules.add(module);
                this.moduleMap.put(module.getModuleName(), module);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modules.sort(Comparator.comparing(Module::getModuleName));

        List<ContentEditor> contentEditors = modules.stream().map(module -> new ContentEditor(module, this)).collect(Collectors.toList());

        GuiHelper helper = GuiHelper.above(this);
        this.activeFile = helper.add(new JTextField(), "Active Plugin", 10, 10, 200, 18);
        this.contentEditors = this.create(new JList<>(contentEditors.toArray(new ContentEditor[0])), "Content Modules", 10, 60, 200, modules.size() * 18);
        this.currentContentObjects = new JList<>();
        this.scrollingContentPane = this.create(new JScrollPane(this.currentContentObjects), "Module Items", 220, 60, 200, 18);

        this.exportPlugin = this.create(new JButton("Export Plugin"), 10, modules.size() * 20 + 70, 200, 40);
//        this.activeFile = this.create(new JTextField(), "Active Plugin", 10, 10, 200, 18);
        this.importPlugin = this.create(new JButton("Import Plugin"), 10, modules.size() * 20 + 120, 200, 40);

        this.contentEditors.setSelectedValue(modules.get(0), true);
        this.updateCurrentObjects(null);

        this.contentEditors.addListSelectionListener(e -> this.updateCurrentObjects(null));
        this.currentContentObjects.addListSelectionListener(e -> this.populateSelected());

        this.exportPlugin.addActionListener(e -> this.export());
        this.importPlugin.addActionListener(e -> this.importFiles(modules));
    }
    public Plugin getActivePlugin() {
        if (!loadedPlugins.containsKey(this.activeFile.getText()) && !this.activeFile.getText().isEmpty()) loadedPlugins.put(this.activeFile.getText(), new Plugin(this.activeFile.getText()));
        return loadedPlugins.getOrDefault(this.activeFile.getText(), null);
    }
    public void populateSelected() {
        String selected = this.getSelectedItem();
        ContentEditor selectedContentEditor = getSelectedContentEditor();
        Module<PipelineObject> selectedModule = selectedContentEditor.getParentModule();
        if(selectedModule.getItemKeys().contains(selected)) {
            PipelineObject item = selectedModule.getObjects().get(selected);
            this.editor.setName(item.getName());
            selectedModule.loadObject(selectedContentEditor, item);
        } else {
            this.editor.setName("");
            selectedModule.loadObject(selectedContentEditor, selectedModule.getDefault());
        }
        this.repaint();
    }
    public Module<? extends PipelineObject> getModule(String name) {
        return this.moduleMap.get(name);
    }
    public void updateCurrentObjects(String name) {
        ContentEditor selectedContentEditor = getSelectedContentEditor();
        if (selectedContentEditor == null) return;

        if (selectedContentEditor != this.editor) {
            if(this.editor != null) this.remove(this.editor);
            this.editor = selectedContentEditor;
            this.add(this.editor);
            selectedContentEditor.getParentModule().onOpened(selectedContentEditor, this);
        }

        List<String> strings = selectedContentEditor.getParentModule().getItemKeys();
        String[] objects = strings.toArray(new String[0]);
        this.currentContentObjects.setListData(objects);
        this.scrollingContentPane.setSize(200, Math.min(600, 18 * Math.max(1, objects.length)));
//        this.currentContentObjects.setSize(200, 18 * Math.max(1, objects.length));
        if(selectedContentEditor.getParentModule().getItemKeys().contains(name)) {
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

    public ContentEditor getSelectedContentEditor() {
        ContentEditor<? extends PipelineObject> selectedValue = this.contentEditors.getSelectedValue();
        return selectedValue;
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
    private void export() {
        Plugin activePlugin = getActivePlugin();
        if(activePlugin == null) {
            JOptionPane.showMessageDialog(null, "Please enter a name for the file!");
            return;
        }
        List<String> dependencies = new ArrayList<>(this.loadedPlugins.keySet());
        dependencies.remove(activePlugin.getPluginName());
        activePlugin.getDependencies().addAll(dependencies);

        File pluginsDirectory = new File("plugins");
        pluginsDirectory.mkdirs();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter pluginWriter = null;
        try {
            File pluginOutput = new File(pluginsDirectory, activePlugin.getPluginName() + ".json");
            pluginOutput.createNewFile();
            pluginWriter = new FileWriter(pluginOutput);
            gson.toJson(activePlugin, pluginWriter);

            JOptionPane.showMessageDialog(null, "Plugin file has been exported!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(pluginWriter);
        }
    }
    private void importFiles(List<Module<? extends PipelineObject>> modules) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("plugins"));
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".json");
            }
            @Override
            public String getDescription() {
                return "Plugin Files";
            }
        });

        chooser.showDialog(null, "Select Plugins");

        if(chooser.getSelectedFiles().length > 0) {
            Gson gson = new GsonBuilder().create();

            File[] pluginFiles = chooser.getSelectedFiles();
            if (!loadedPlugins.isEmpty()) loadedPlugins.clear();
            List<Plugin> plugins = Arrays.asList(pluginFiles).stream().map(file -> loadPlugin(gson, file)).collect(Collectors.toList());
            for (Plugin plugin : plugins) loadedPlugins.putIfAbsent(plugin.getPluginName(), plugin);

            plugins.sort(null);

            for(Module<? extends PipelineObject> module : modules) {
                for(Plugin plugin : plugins) {
                    module.loadContent(plugin, objectOwners);
                }
            }
        }
//        this.updateCurrentObjects(null);
    }
    private Plugin loadPlugin(Gson gson, File pluginFile) {
        Reader reader = null;
        try {
            reader = new FileReader(pluginFile);
            return gson.fromJson(reader, Plugin.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return null;
    }
}