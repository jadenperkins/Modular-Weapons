package com.main.pluginbuilder.contenteditors;

import com.main.pipeline.PipelineObjectMaterialType;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialTypeEditor extends ContentEditor<PipelineObjectMaterialType> {

    private final JComboBox<String> scriptSelection;

    public MaterialTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<PipelineObjectMaterialType> parent, PluginBuilderPanel panel) {
        List<String> scripts = this.getScripts("material types", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }
    @Override
    public void populate(PipelineObjectMaterialType item) {
        this.scriptSelection.setSelectedItem(item.getScript());
    }
    @Override
    public PipelineObjectMaterialType createItem(String name) {
        return new PipelineObjectMaterialType(name, (String)this.scriptSelection.getSelectedItem());
    }
    @Override
    public PipelineObjectMaterialType getDefault() {
        return new PipelineObjectMaterialType("", "");
    }
}