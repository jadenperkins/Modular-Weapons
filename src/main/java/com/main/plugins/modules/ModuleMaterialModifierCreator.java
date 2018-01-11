package com.main.plugins.modules;

import com.main.constants.Colors;
import com.main.constants.MaterialModifiers;
import com.main.constants.MaterialTypes;
import com.main.material.MaterialModifier;
import com.main.material.MaterialType;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.builder.component.BuilderListBox;
import com.main.plugins.builder.component.BuilderTextField;
import com.main.plugins.pipeline.PipelineObjectMaterialModifier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialModifierCreator extends Module<PipelineObjectMaterialModifier> {
    public ModuleMaterialModifierCreator() {
        super("Material Modifiers", 6, Plugin::getMaterialModifiers);
    }
    @Override
    public PipelineObjectMaterialModifier createItem(ContentEditor<PipelineObjectMaterialModifier> contentEditor, String name) {
        String colorName = (String) contentEditor.getComponent("colorSelection").getAsComboBox().getSelectedItem();
        float weight = contentEditor.getComponent("weightField").getAsTextField().floatValue();
        float level = contentEditor.getComponent("levelField").getAsTextField().floatValue();
        float mod = contentEditor.getComponent("modField").getAsTextField().floatValue();
        List<String> values = (List<String>) contentEditor.getComponent("materialsList").getAsListBox().getSelectedValuesList();

        return new PipelineObjectMaterialModifier(name, colorName, weight, level, mod, values);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectMaterialModifier> contentEditor) {
        contentEditor.addComponent("colorSelection", new BuilderComboBox<String>(), H_S, V_E, H_L, H_FLD);
        contentEditor.addComponent("weightField", new BuilderTextField(), H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        contentEditor.addComponent("levelField", new BuilderTextField(), H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("modField", new BuilderTextField(), H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("materialsList", new BuilderListBox<String>(), H_S, V_E + 4 * (H_FLD + V_PAD), H_L, 10 * H_FLD);
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectMaterialModifier> contentEditor, PipelineObjectMaterialModifier pipelineObject) {
        contentEditor.getComponent("colorSelection").getAsComboBox().setSelectedItem(pipelineObject.getColor());
        contentEditor.getComponent("weightField").getAsTextField().setText(pipelineObject.getWeight() + "");
        contentEditor.getComponent("levelField").getAsTextField().setText(pipelineObject.getLevel() + "");
        contentEditor.getComponent("modField").getAsTextField().setText(pipelineObject.getMod() + "");

        List<String> materialTypes = pipelineObject.getMaterials();
        List<Integer> indices = new ArrayList<>();

        ListModel listModel = contentEditor.getComponent("materialsList").getAsListBox().getModel();
        int size = listModel.getSize();
        for (String materialType : materialTypes)
            for(int i = 0; i < size; i++)
                if(listModel.getElementAt(i).equals(materialType))
                    indices.add(i);

        int[] i = new int[indices.size()];
        for(int x = 0; x < i.length; x++) {
            i[x] = indices.get(x);
        }

        contentEditor.getComponent("materialsList").getAsListBox().setSelectedIndices(i);
    }
    @Override
    public PipelineObjectMaterialModifier getDefault() {
        return new PipelineObjectMaterialModifier("", "", 0F, 0F, 0F, new ArrayList<>());
    }

    @Override
    public void onOpened(ContentEditor<PipelineObjectMaterialModifier> contentEditor, PluginBuilderPanel panel) {
        Module colorModule = panel.getModule("Colors");
        List<String> colors = colorModule.getItemKeys();
        contentEditor.getComponent("colorSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(colors.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("Material Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        contentEditor.getComponent("materialsList").getAsListBox().setListData(materialTypes.toArray(new String[0]));
        contentEditor.getComponent("materialsList").getAsListBox().setSize(200, 18 * Math.max(1, materialTypes.size()));
    }
    @Override
    public void consume(PipelineObjectMaterialModifier object) {
        Color c = Colors.get(object.getColor());
        float w = object.getWeight();
        float l = object.getLevel();
        float m = object.getMod();
        List<MaterialType> materialTypes = object.getMaterials().stream().map(MaterialTypes::get).collect(Collectors.toList());
        MaterialModifier modifier = new MaterialModifier(object.getName(), c, w, m, l, materialTypes);
        materialTypes.forEach(type -> MaterialModifiers.register(type, modifier));
    }
}