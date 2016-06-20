package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;
import com.jadencode.main.pluginbuilder.items.ItemWeaponType;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialModifierEditor extends ContentEditor<ItemMaterialModifier> {

//    "color": "MOD_LUNAR", "weight": 1.0, "level": 0.5, "mod": 1.2, "materials": ["Metal"]
    private final JTextField colorName;
    private final JTextField weightField;
    private final JTextField levelField;
    private final JTextField modField;
    private final JTable materialTable;

    public MaterialModifierEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.colorName = this.create(new JTextField(), 10, 140, 200, 18);
        this.weightField = this.create(new JTextField(), 10, 160, 200, 18);
        this.levelField = this.create(new JTextField(), 10, 180, 200, 18);
        this.modField = this.create(new JTextField(), 10, 200, 200, 18);
        this.materialTable = this.create(new JTable(new DefaultTableModel(10, 1)), 10, 220, 200, 160);
    }
    @Override
    public void populate(ItemMaterialModifier item) {
        this.colorName.setText(item.getColorName());
        this.weightField.setText(item.getWeight() + "");
        this.levelField.setText(item.getLevel() + "");
        this.modField.setText(item.getMod() + "");

        this.materialTable.setModel(new DefaultTableModel(10, 1));
        List<String> materialTypes = item.getMaterialTypes();
        for(int row = 0; row < materialTypes.size(); row++) {
            String name = materialTypes.get(row);
            this.materialTable.getModel().setValueAt(name, row, 0);
        }
    }
    @Override
    public ItemMaterialModifier createItem(String name) {
        String colorName = this.colorName.getText();
        float weight = this.getValue(this.weightField);
        float level = this.getValue(this.levelField);
        float mod = this.getValue(this.modField);

        int rows = this.materialTable.getModel().getRowCount();
        List<String> materialTypes = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            String part = (String)this.materialTable.getValueAt(row, 0);
            if(part != null && !part.isEmpty()) {
                materialTypes.add(part);
            }
        }
        return new ItemMaterialModifier(name, colorName, weight, level, mod, materialTypes);
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
    public ItemMaterialModifier getDefault() {
        return new ItemMaterialModifier("", "", 0F, 0F, 0F, new ArrayList<>());
    }

    @Override
    public ItemMaterialModifier consume(String name, JsonObject json) {
        String color = json.get("color").getAsString();
        float weight = json.has("weight") ? json.get("weight").getAsFloat() : 1F;
        float level = json.get("level").getAsFloat();
        float mod = json.get("mod").getAsFloat();
        List<String> types = new ArrayList<>();
        JsonArray array = json.get("materials").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            types.add(jsonElement.getAsString());
        }
        return new ItemMaterialModifier(name, color, weight, level, mod, types);
    }
}