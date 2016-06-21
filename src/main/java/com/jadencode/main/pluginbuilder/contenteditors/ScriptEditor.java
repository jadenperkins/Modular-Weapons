package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemScript;
import com.jadencode.main.pluginbuilder.modules.Module;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ScriptEditor extends ContentEditor<ItemScript> {
    private static final String[] SCRIPT_TYPES = {
            "material types", "weapons", "stats"
    };

    private final JButton selectScript;
    private final JTextArea scriptView;
    private final JComboBox<String> scriptTypeSelection;

    private final JFileChooser scriptChooser = new JFileChooser();

    public ScriptEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);

        this.scriptChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".js");
            }
            @Override
            public String getDescription() {
                return "JavaScript Files";
            }
        });
        this.scriptView = this.createScrolling(new JTextArea(), 220, 10, 750, 650);
        this.scriptTypeSelection = this.create(new JComboBox<>(SCRIPT_TYPES), 10, 130, 180, 18);

        this.selectScript = this.create(new JButton("Select Script"), 10, 150, 200, 40);
        this.selectScript.addActionListener(e -> {
            this.scriptChooser.showOpenDialog(null);
            if(this.scriptChooser.getSelectedFile() != null) {
                File file = this.scriptChooser.getSelectedFile();
                try {
                    this.scriptView.setText(FileUtils.readFileToString(file, Charset.defaultCharset()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    @Override
    public void populate(ItemScript item) {
        this.scriptTypeSelection.setSelectedItem(item.getScriptType());
        this.scriptView.setText(item.getScriptContents());
    }
    @Override
    public ItemScript createItem(String name, String owner) {
        return new ItemScript(name, owner, (String) this.scriptTypeSelection.getSelectedItem(), this.scriptView.getText());
    }
    @Override
    public ItemScript getDefault() {
        return new ItemScript("", "", "", "");
    }

    @Override
    public ItemScript consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        return new ItemScript(name, owner, helper.getString("type"), helper.getString("script"));
    }
}