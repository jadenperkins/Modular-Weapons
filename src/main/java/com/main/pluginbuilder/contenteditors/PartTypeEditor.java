package com.main.pluginbuilder.contenteditors;

import com.main.pipeline.PipelineObjectPartType;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PartTypeEditor extends ContentEditor<PipelineObjectPartType> {

    private final JComboBox<String> iconSelection;

    public PartTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<PipelineObjectPartType> parent, PluginBuilderPanel panel) {
        Module iconModules = panel.getModule("Icons");
        List<String> icons = iconModules.getItemKeys();
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));
    }
    @Override
    public void populate(PipelineObjectPartType item) {
        this.iconSelection.setSelectedItem(item.getIcon());
    }
    @Override
    public PipelineObjectPartType createItem(String name) {
        return new PipelineObjectPartType(name, (String)this.iconSelection.getSelectedItem());
    }
    @Override
    public PipelineObjectPartType getDefault() {
        return new PipelineObjectPartType("", "");
    }
}