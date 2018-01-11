package com.main.plugins.modules;

import com.main.constants.*;
import com.main.generate.weapon.WeaponPartBase;
import com.main.generate.weapon.WeaponPartLegendary;
import com.main.generate.weapon.WeaponPartType;
import com.main.material.MaterialType;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.builder.component.BuilderListBox;
import com.main.plugins.builder.component.BuilderTextField;
import com.main.plugins.pipeline.PipelineObjectWeaponPart;
import com.main.stat.StatSet;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleWeaponPartCreator extends Module<PipelineObjectWeaponPart> {
    public ModuleWeaponPartCreator() {
        super("Weapon Parts", 8, Plugin::getWeaponParts);
    }
    @Override
    public PipelineObjectWeaponPart createItem(ContentEditor<PipelineObjectWeaponPart> contentEditor, String name) {
        String nameMod = contentEditor.getComponent("nameModField").getAsTextField().getText();
        String partInfo = contentEditor.getComponent("partInfo").getAsTextField().getText();
        float weight = contentEditor.getComponent("weightField").getAsTextField().floatValue();
        String partType = (String) contentEditor.getComponent("partTypeSelection").getAsComboBox().getSelectedItem();
        String statSet = (String) contentEditor.getComponent("statSetSelection").getAsComboBox().getSelectedItem();
        String iconName = (String) contentEditor.getComponent("iconSelection").getAsComboBox().getSelectedItem();

        List<String> materialTypes = (List<String>) contentEditor.getComponent("materialsList").getAsListBox().getSelectedValuesList();
        return new PipelineObjectWeaponPart(name, nameMod, partInfo, weight, partType, statSet, iconName, materialTypes);
    }

    @Override
    public void populatePanel(ContentEditor<PipelineObjectWeaponPart> contentEditor) {
        contentEditor.addComponent("nameModField", new BuilderTextField(), H_S, V_E, H_L, H_FLD);
        contentEditor.addComponent("partInfoField", new BuilderTextField(), H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        contentEditor.addComponent("weightField", new BuilderTextField(), H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("partTypeSelection", new BuilderComboBox<>(), H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("statSetSelection", new BuilderComboBox<>(), H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("iconSelection", new BuilderComboBox<>(), H_S, V_E + 5 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("materialsList", new BuilderListBox<>(), H_E, V_S, H_L, H_FLD * 10);
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectWeaponPart> contentEditor, PipelineObjectWeaponPart pipelineObject) {
        contentEditor.getComponent("nameModField").getAsTextField().setText(pipelineObject.getNameMod());
        contentEditor.getComponent("partInfoField").getAsTextField().setText(pipelineObject.getPartInfo());
        contentEditor.getComponent("weightField").getAsTextField().setText(pipelineObject.getWeight() + "");
        contentEditor.getComponent("partTypeSelection").getAsComboBox().setSelectedItem(pipelineObject.getPartType());
        contentEditor.getComponent("statSetSelection").getAsComboBox().setSelectedItem(pipelineObject.getStats());
        contentEditor.getComponent("iconSelection").getAsComboBox().setSelectedItem(pipelineObject.getIcon());

        List<String> materialTypes = pipelineObject.getMaterials();
        List<Integer> indices = new ArrayList<>();

        BuilderListBox<String> materialsList = contentEditor.getComponent("materialsList").getAsListBox();

        int size = materialsList.getModel().getSize();
        for (String materialType : materialTypes)
            for(int i = 0; i < size; i++)
                if(materialsList.getModel().getElementAt(i).equals(materialType))
                    indices.add(i);

        int[] i = new int[indices.size()];
        for(int x = 0; x < i.length; x++) {
            i[x] = indices.get(x);
        }
        materialsList.setSelectedIndices(i);
    }

    @Override
    public void onOpened(ContentEditor<PipelineObjectWeaponPart> contentEditor, PluginBuilderPanel panel) {
        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = partTypesModule.getItemKeys();
        contentEditor.getComponent("partTypeSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        Module statSetsModule = panel.getModule("Stat Sets");
        List<String> statSets = new ArrayList<>();
        statSets.add("");
        statSets.addAll(statSetsModule.getItemKeys());
        contentEditor.getComponent("statSetSelection").getAsComboBox().setModel(new DefaultComboBoxModel(statSets.toArray(new String[0])));

        Module iconsModule = panel.getModule("Icons");
        List<String> icons = new ArrayList<>();
        icons.add("");
        icons.addAll(iconsModule.getItemKeys());
        contentEditor.getComponent("iconSelection").getAsComboBox().setModel(new DefaultComboBoxModel(icons.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("Material Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        BuilderListBox materialsList = contentEditor.getComponent("materialsList").getAsListBox();
        materialsList.setListData(materialTypes.toArray(new String[0]));
        materialsList.setSize(H_L, H_FLD * Math.max(1, materialTypes.size()));
    }

    @Override
    public PipelineObjectWeaponPart getDefault() {
        return new PipelineObjectWeaponPart("", "", "", 0F, "", "", "", new ArrayList<>());
    }

    @Override
    public void consume(PipelineObjectWeaponPart obj) {
        boolean isLegendary = obj.getMaterials() == null || obj.getMaterials().isEmpty();
        String nameMod = obj.getNameMod();
        String partInfo = obj.getPartInfo();
        String stats = obj.getStats();
        StatSet set = stats == null ? StatSets.EMPTY : StatSets.get(stats);
        float weight = obj.getWeight();
        String iconName = obj.getIcon();
        BufferedImage icon = Icons.get(iconName);
        WeaponPartType type = PartTypes.get(obj.getPartType());

        if(isLegendary) {
            WeaponParts.register(new WeaponPartLegendary(obj.getName(), nameMod, partInfo, set, weight, icon, type));
        } else {
            List<String> materials = obj.getMaterials();
            List<MaterialType> types = materials.stream().map(MaterialTypes::get).collect(Collectors.toList());
            WeaponParts.addBasePart(new WeaponPartBase(obj.getName(), nameMod, weight, set, type, icon, types));
        }
    }
}