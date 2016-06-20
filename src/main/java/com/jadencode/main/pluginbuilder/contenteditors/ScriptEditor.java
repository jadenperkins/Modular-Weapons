package com.jadencode.main.pluginbuilder.contenteditors;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemColor;
import com.jadencode.main.pluginbuilder.items.ItemScript;
import com.jadencode.main.pluginbuilder.modules.Module;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ScriptEditor extends ContentEditor<ItemScript> {

    private static final ItemScript DEFAULT = new ItemScript("Default", "", "");

    private final JButton selectScript;
    private final JTextField scriptType;
    private final JTextArea scriptView;

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
        this.scriptView = new JTextArea();
        this.scriptView.setEditable(false);
        JScrollPane pane = this.create(new JScrollPane(this.scriptView), 220, 10, 750, 650);

        this.add(pane);

        this.scriptType = this.create(new JTextField(), 10, 130, 200, 18);

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
    }
    @Override
    public ItemScript createItem(String name) {
        return new ItemScript(name, this.scriptType.getText(), this.scriptView.getText());
    }
    @Override
    public ItemScript getDefault() {
        return DEFAULT;
    }
}