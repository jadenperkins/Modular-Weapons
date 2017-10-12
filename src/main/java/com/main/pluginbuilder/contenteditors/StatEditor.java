package com.main.pluginbuilder.contenteditors;

import com.main.pipeline.PipelineObjectStat;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatEditor extends ContentEditor<PipelineObjectStat> {

    private final JComboBox<String> scriptSelection;
    private final JTextField defaultValue;

    public StatEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E, H_L, H_FLD);
        this.defaultValue = helper.add(new JTextField(), "Default Value", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<PipelineObjectStat> parent, PluginBuilderPanel panel) {
        List<String> scripts = this.getScripts("stats", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }
    @Override
    public void populate(PipelineObjectStat item) {
        this.scriptSelection.setSelectedItem(item.getScript());
        this.defaultValue.setText(item.getDefaultValue() + "");
    }
    @Override
    public PipelineObjectStat createItem(String name) {
        double value;
        try {
            value = Double.parseDouble(this.defaultValue.getText());
        } catch (Exception e) {
            value = 0;
            this.defaultValue.setText("0.0");
        }
        return new PipelineObjectStat(name, (String) this.scriptSelection.getSelectedItem(), value);
    }
    @Override
    public PipelineObjectStat getDefault() {
        return new PipelineObjectStat("", "", 0.0);
    }
}