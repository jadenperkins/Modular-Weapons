package com.main.plugins.builder.contenteditors;

import com.main.plugins.builder.GuiHelper;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.modules.Module;
import com.main.plugins.pipeline.PipelineObjectWeaponPart;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class WeaponPartEditor extends ContentEditor<PipelineObjectWeaponPart> {

    private final JTextField nameModField;
    private final JTextField partInfoField;
    private final JTextField weightField;
    private final JComboBox<String> partTypeSelection;
    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> iconSelection;
    private final JList<String> materialsList;

    public WeaponPartEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.nameModField = helper.add(new JTextField(), "Name Modifier", H_S, V_E, H_L, H_FLD);
        this.partInfoField = helper.add(new JTextField(), "Part Info", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partTypeSelection = helper.add(new JComboBox<>(), "Part Type", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E + 5 * (H_FLD + V_PAD), H_L, H_FLD);
        this.materialsList = helper.add(new JList<>(), "MaterialBase Types", H_E, V_S, H_L, H_FLD * 10, GuiHelper.Align.ABOVE);
    }
    @Override
    public void onOpened(Module<PipelineObjectWeaponPart> parent, PluginBuilderPanel panel) {
        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = partTypesModule.getItemKeys();
        this.partTypeSelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        Module statSetsModule = panel.getModule("Stat Sets");
        List<String> statSets = new ArrayList<>();
        statSets.add("");
        statSets.addAll(statSetsModule.getItemKeys());
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        Module iconsModule = panel.getModule("Icons");
        List<String> icons = new ArrayList<>();
        icons.add("");
        icons.addAll(iconsModule.getItemKeys());
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("MaterialBase Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        this.materialsList.setListData(materialTypes.toArray(new String[0]));
        this.materialsList.setSize(H_L, H_FLD * Math.max(1, materialTypes.size()));
    }
    @Override
    public void populate(PipelineObjectWeaponPart item) {
        this.nameModField.setText(item.getNameMod());
        this.partInfoField.setText(item.getPartInfo());
        this.weightField.setText(item.getWeight() + "");
        this.partTypeSelection.setSelectedItem(item.getPartType());
        this.statSetSelection.setSelectedItem(item.getStats());
        this.iconSelection.setSelectedItem(item.getIcon());

        List<String> materialTypes = item.getMaterials();
        List<Integer> indices = new ArrayList<>();

        for (String materialType : materialTypes)
            for(int i = 0; i < this.materialsList.getModel().getSize(); i++)
                if(this.materialsList.getModel().getElementAt(i).equals(materialType))
                    indices.add(i);

        int[] i = new int[indices.size()];
        for(int x = 0; x < i.length; x++) {
            i[x] = indices.get(x);
        }

        this.materialsList.setSelectedIndices(i);
    }
    @Override
    public PipelineObjectWeaponPart createItem(String name) {
        String nameMod = this.nameModField.getText();
        String partInfo = this.partInfoField.getText();
        float weight = this.getValue(this.weightField);
        String partType = (String) this.partTypeSelection.getSelectedItem();
        String statSet = (String) this.statSetSelection.getSelectedItem();
        String iconName = (String) this.iconSelection.getSelectedItem();

        List<String> materialTypes = this.materialsList.getSelectedValuesList();
        return new PipelineObjectWeaponPart(name, nameMod, partInfo, weight, partType, statSet, iconName, materialTypes);
    }
    private float getValue(JTextField field) {
        float value;
        try {
            value = Float.parseFloat(field.getText());
        } catch (Exception e) {
            value = 0F;
        }
        return value;
    }
    @Override
    public PipelineObjectWeaponPart getDefault() {
        return new PipelineObjectWeaponPart("", "", "", 0F, "", "", "", new ArrayList<>());
    }
}