package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemWeaponPart;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class WeaponPartEditor extends ContentEditor<ItemWeaponPart> {

    private final JTextField nameModField;
    private final JTextField partInfoField;
    private final JTextField weightField;
    private final JComboBox<String> partTypeSelection;
    private final JList<String> materialsList;

    public WeaponPartEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.nameModField = this.create(new JTextField(), 10, 140, 200, 18);
        this.partInfoField = this.create(new JTextField(), 10, 160, 200, 18);
        this.weightField = this.create(new JTextField(), 10, 180, 200, 18);
        this.partTypeSelection = this.create(new JComboBox<>(), 10, 200, 200, 18);
        this.materialsList = this.createScrolling(new JList<>(), 10, 220, 200, 160);
    }
    @Override
    public void onOpened(Module<ItemWeaponPart> parent, PluginBuilderPanel panel) {
        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = partTypesModule.getItemKeys();
        this.partTypeSelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("Material Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        this.materialsList.setListData(materialTypes.toArray(new String[0]));
        this.materialsList.setSize(200, 18 * Math.max(1, materialTypes.size()));
    }
    @Override
    public void populate(ItemWeaponPart item) {
        this.nameModField.setText(item.getNameMod());
        this.partInfoField.setText(item.getPartInfo());
        this.weightField.setText(item.getWeight() + "");
        this.partTypeSelection.setSelectedItem(item.getPartType());

        List<String> materialTypes = item.getMaterialTypes();
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
    public ItemWeaponPart createItem(String name) {
        String nameMod = this.nameModField.getText();
        String partInfo = this.partInfoField.getText();
        float weight = this.getValue(this.weightField);
        String partType = (String) this.partTypeSelection.getSelectedItem();

        List<String> materialTypes = this.materialsList.getSelectedValuesList();
        return new ItemWeaponPart(name, nameMod, partInfo, weight, partType, materialTypes);
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
    public ItemWeaponPart getDefault() {
        return new ItemWeaponPart("", "", "", 0F, "", new ArrayList<>());
    }

    @Override
    public ItemWeaponPart consume(String name, JsonObject json) {
        JsonHelper helper = new JsonHelper(json);
        String nameMod = helper.getString("nameMod");
        String partInfo = helper.getString("partInfo");
        float weight = helper.getFloat("weight", 1F);
        String partType = helper.getString("partType");
        List<String> materials = new ArrayList<>();
        JsonArray array = helper.getArray("materials");

        for (JsonElement jsonElement : array)
            materials.add(jsonElement.getAsString());

        return new ItemWeaponPart(name, nameMod, partInfo, weight, partType, materials);
    }
}