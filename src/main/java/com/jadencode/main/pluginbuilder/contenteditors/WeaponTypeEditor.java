package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
    private final JTable partsTable;

    public WeaponTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.statSet = this.create(new JTextField(), 10, 140, 200, 18);
        this.scriptName = this.create(new JTextField(), 10, 160, 200, 18);
        this.partsTable = this.create(new JTable(new DefaultTableModel(10, 1)), 10, 180, 200, 160);
    }
    @Override
    public void populate(ItemWeaponType item) {

        this.statSet.setText(item.getStatSetName());
        this.scriptName.setText(item.getScriptName());

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

        int rows = this.partsTable.getModel().getRowCount();
        List<String> partNames = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            String part = (String)this.partsTable.getValueAt(row, 0);
            if(part != null && !part.isEmpty()) {
                partNames.add(part);
            }
        }
        return new ItemWeaponType(name, stat, script, partNames);
    }
    @Override
    public ItemWeaponType getDefault() {
        return new ItemWeaponType("", "", "", new ArrayList<>());
    }

    @Override
    public ItemWeaponType consume(String name, JsonObject json) {
        String statSet = json.get("stats").getAsString();
        String script = json.has("script") ? json.get("script").getAsString() : "";
        List<String> parts = new ArrayList<>();
        JsonArray array = json.get("parts").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            parts.add(jsonElement.getAsString());
        }
        return new ItemWeaponType(name, statSet, script, parts);
    }
}