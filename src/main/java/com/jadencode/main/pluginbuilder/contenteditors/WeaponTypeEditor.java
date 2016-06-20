package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemWeaponType;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class WeaponTypeEditor extends ContentEditor<ItemWeaponType> {

    private final JTextField statSet;
    private final JTextField scriptName;
    private final JTextField weightField;
    private final JTable partsTable;

    public WeaponTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.statSet = this.create(new JTextField(), 10, 140, 200, 18);
        this.scriptName = this.create(new JTextField(), 10, 160, 200, 18);
        this.weightField = this.create(new JTextField(), 10, 180, 200, 18);
        this.partsTable = this.create(new JTable(new DefaultTableModel(10, 1)), 10, 200, 200, 160);
    }
    @Override
    public void populate(ItemWeaponType item) {

        this.statSet.setText(item.getStatSetName());
        this.scriptName.setText(item.getScriptName());
        this.weightField.setText(item.getWeight() + "");

        this.partsTable.setModel(new DefaultTableModel(10, 1));
        List<String> partTypes = item.getPartTypes();
        for(int row = 0; row < partTypes.size(); row++) {
            String name = partTypes.get(row);
            this.partsTable.getModel().setValueAt(name, row, 0);
        }
    }
    @Override
    public ItemWeaponType createItem(String name) {
        String stat = this.statSet.getText();
        String script = this.scriptName.getText();
        float weight = this.getFloat(this.weightField);

        int rows = this.partsTable.getModel().getRowCount();
        List<String> partNames = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            String part = (String)this.partsTable.getValueAt(row, 0);
            if(part != null && !part.isEmpty()) {
                partNames.add(part);
            }
        }
        return new ItemWeaponType(name, stat, script, weight, partNames);
    }
    public float getFloat(JTextField field) {
        float value;
        try {
            value = Float.parseFloat(field.getText());
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }
    @Override
    public ItemWeaponType getDefault() {
        return new ItemWeaponType("", "", "", 1F, new ArrayList<>());
    }

    @Override
    public ItemWeaponType consume(String name, JsonObject json) {
        JsonHelper helper = new JsonHelper(json);
        String statSet = helper.getString("stats");
        String script = helper.getString("script");
        float weight = helper.getFloat("weight");
        List<String> parts = new ArrayList<>();
        JsonArray array = helper.getArray("parts");
        for (JsonElement jsonElement : array)
            parts.add(jsonElement.getAsString());

        return new ItemWeaponType(name, statSet, script, weight, parts);
    }
}