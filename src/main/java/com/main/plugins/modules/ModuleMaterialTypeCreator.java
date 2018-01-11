package com.main.plugins.modules;

import com.main.constants.MaterialTypes;
import com.main.material.MaterialType;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.pipeline.PipelineObjectMaterialType;
import com.main.scripts.ScriptMaterialType;

import javax.swing.*;
import java.util.List;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialTypeCreator extends Module<PipelineObjectMaterialType> {
    private static final String SCRIPT_SELECTION = "scriptSelection";

    public ModuleMaterialTypeCreator() {
        super("Material Types", 0, Plugin::getMaterialTypes);
    }

    @Override
    public PipelineObjectMaterialType createItem(ContentEditor<PipelineObjectMaterialType> contentEditor, String name) {
        String script = (String) contentEditor.getComponent(SCRIPT_SELECTION).getAsComboBox().getSelectedItem();
        return new PipelineObjectMaterialType(name, script);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectMaterialType> contentEditor) {
        contentEditor.addComponent(SCRIPT_SELECTION, new BuilderComboBox<>(), H_S, V_E, H_L, H_FLD);
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectMaterialType> contentEditor, PipelineObjectMaterialType pipelineObject) {
        contentEditor.getComponent(SCRIPT_SELECTION).getAsComboBox().setSelectedItem(pipelineObject.getScript());
    }
    @Override
    public void onOpened(ContentEditor<PipelineObjectMaterialType> contentEditor, PluginBuilderPanel panel) {
        List<String> scripts = contentEditor.getScripts("material types", panel);
        contentEditor.getComponent(SCRIPT_SELECTION).getAsComboBox().setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }
    @Override
    public PipelineObjectMaterialType getDefault() {
        return new PipelineObjectMaterialType("", "");
    }

    @Override
    public void consume(PipelineObjectMaterialType object) {
        String scriptName = object.getScript();
        ScriptMaterialType script = MaterialTypes.script(scriptName);
        MaterialTypes.register(new MaterialType(object.getName(), script));
    }
}