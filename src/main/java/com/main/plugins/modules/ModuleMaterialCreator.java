package com.main.plugins.modules;

import com.main.constants.Colors;
import com.main.constants.MaterialTypes;
import com.main.constants.Materials;
import com.main.generate.QualityLevel;
import com.main.material.Material;
import com.main.material.MaterialBase;
import com.main.material.MaterialType;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.builder.component.BuilderTextField;
import com.main.plugins.pipeline.PipelineObjectMaterial;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialCreator extends Module<PipelineObjectMaterial> {
    public ModuleMaterialCreator() {
        super("Materials", 7, Plugin::getMaterials);
    }

    @Override
    public PipelineObjectMaterial createItem(ContentEditor<PipelineObjectMaterial> contentEditor, String name) {
        String colorName = (String) contentEditor.getComponent("colorSelection").getAsComboBox().getSelectedItem();
        float weight = contentEditor.getComponent("weightField").getAsTextField().floatValue();
        float mod = contentEditor.getComponent("modField").getAsTextField().floatValue();
        int level = contentEditor.getComponent("levelField").getAsTextField().intValue();
        String material = (String) contentEditor.getComponent("materialSelection").getAsComboBox().getSelectedItem();

        return new PipelineObjectMaterial(name, colorName, weight, mod, level, material);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectMaterial> contentEditor) {
        contentEditor.addComponent("colorSelection", new BuilderComboBox<>(), H_S, V_E, H_L, H_FLD);
        contentEditor.addComponent("weightField", new BuilderTextField(), H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        contentEditor.addComponent("modField", new BuilderTextField(), H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("levelField", new BuilderTextField(), H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("materialSelection", new BuilderComboBox<>(), H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectMaterial> contentEditor, PipelineObjectMaterial pipelineObject) {
        contentEditor.getComponent("colorSelection").getAsComboBox().setSelectedItem(pipelineObject.getColor());
        contentEditor.getComponent("weightField").getAsTextField().setText(pipelineObject.getWeight() + "");
        contentEditor.getComponent("modField").getAsTextField().setText(pipelineObject.getMod() + "");
        contentEditor.getComponent("levelField").getAsTextField().setText(pipelineObject.getLevel() + "");
        contentEditor.getComponent("materialSelection").getAsComboBox().setSelectedItem(pipelineObject.getMaterial());
    }

    @Override
    public void onOpened(ContentEditor<PipelineObjectMaterial> contentEditor, PluginBuilderPanel panel) {
        Module colorModule = panel.getModule("Colors");
        List<String> colors = colorModule.getItemKeys();
        contentEditor.getComponent("colorSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(colors.toArray(new String[0])));

        Module materalTypesModule = panel.getModule("Material Types");
        List<String> types = materalTypesModule.getItemKeys();
        contentEditor.getComponent("materialSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(types.toArray(new String[0])));
    }
    @Override
    public PipelineObjectMaterial getDefault() {
        return new PipelineObjectMaterial("", "", 0F, 0F, 0, "");
    }

    @Override
    public void consume(PipelineObjectMaterial object) {
        Color c = Colors.get(object.getColor());
        float w = object.getWeight();
        float m = object.getMod();
        int l = object.getLevel();
        MaterialType t = MaterialTypes.get(object.getMaterial());
        Material material = new MaterialBase(object.getName(), c, w, m, l, QualityLevel.COMMON, t);
        Materials.register(t, material);
    }
}