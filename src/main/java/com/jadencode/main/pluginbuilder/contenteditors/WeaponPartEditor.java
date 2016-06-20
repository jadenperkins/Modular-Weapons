package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;
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

//    "nameModField": "Seeker", "partInfo": "A legendary weapon part", "weight": 100.0, "partType": "Sword Blade"},
//    "nameModField": "Double", "partType": "Sword Grip", "materials": ["Metal"]},

    private final JTextField nameModField;
    private final JTextField partInfoField;
    private final JTextField weightField;
    private final JTextField partTypeField;
    private final JTable materialTable;

    public WeaponPartEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.nameModField = this.create(new JTextField(), 10, 140, 200, 18);
        this.partInfoField = this.create(new JTextField(), 10, 160, 200, 18);
        this.weightField = this.create(new JTextField(), 10, 180, 200, 18);
        this.partTypeField = this.create(new JTextField(), 10, 200, 200, 18);
        this.materialTable = this.create(new JTable(new DefaultTableModel(10, 1)), 10, 220, 200, 160);
    }
    @Override
    public void populate(ItemWeaponPart item) {
        this.nameModField.setText(item.getNameMod());
        this.partInfoField.setText(item.getPartInfo());
        this.weightField.setText(item.getWeight() + "");
        this.partTypeField.setText(item.getPartType());

        this.materialTable.setModel(new DefaultTableModel(10, 1));
        List<String> materialTypes = item.getMaterialTypes();
        for(int row = 0; row < materialTypes.size(); row++) {
            String name = materialTypes.get(row);
            this.materialTable.getModel().setValueAt(name, row, 0);
        }
    }
    @Override
    public ItemWeaponPart createItem(String name) {
        String nameMod = this.nameModField.getText();
        String partInfo = this.partInfoField.getText();
        float weight = this.getValue(this.weightField);
        String partType = this.partTypeField.getText();

        int rows = this.materialTable.getModel().getRowCount();
        List<String> materialTypes = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            String part = (String)this.materialTable.getValueAt(row, 0);
            if(part != null && !part.isEmpty()) {
                materialTypes.add(part);
            }
        }
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
        String nameMod = json.get("nameMod").getAsString();
        String partInfo = json.has("partInfo") ? json.get("partInfo").getAsString() : "";
        float weight = json.has("weight") ? json.get("weight").getAsFloat() : 1F;
        String partType = json.get("partType").getAsString();
        List<String> materials = new ArrayList<>();
        if(json.has("materials")) {
            JsonArray array = json.get("materials").getAsJsonArray();
            for (JsonElement jsonElement : array)
                materials.add(jsonElement.getAsString());
        }

        return new ItemWeaponPart(name, nameMod, partInfo, weight, partType, materials);
    }
}