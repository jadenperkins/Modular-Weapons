package com.main.plugins.builder.contenteditors;

import com.main.plugins.builder.GuiHelper;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.modules.Module;
import com.main.plugins.pipeline.PipelineObjectScript;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ScriptEditor extends ContentEditor<PipelineObjectScript> {
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
        GuiHelper helper = GuiHelper.left(this);
        this.scriptView = helper.addScrolling(new JTextArea(), "Script Editor", H_E, V_S, 650, 650, GuiHelper.Align.ABOVE);

        this.selectScript = helper.add(new JButton("Import Script"), H_S, V_E, H_L, H_BTN);
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

        this.scriptTypeSelection = helper.add(new JComboBox<>(SCRIPT_TYPES), "Script Type", H_S, V_E + H_BTN + V_PAD, H_L, H_FLD);
    }
    @Override
    public void populate(PipelineObjectScript item) {
        this.scriptTypeSelection.setSelectedItem(item.getType());
        this.scriptView.setText(item.getScript());
    }
    @Override
    public PipelineObjectScript createItem(String name) {
        return new PipelineObjectScript(name, (String) this.scriptTypeSelection.getSelectedItem(), this.scriptView.getText());
    }
    @Override
    public PipelineObjectScript getDefault() {
        return new PipelineObjectScript("", "", "");
    }
}