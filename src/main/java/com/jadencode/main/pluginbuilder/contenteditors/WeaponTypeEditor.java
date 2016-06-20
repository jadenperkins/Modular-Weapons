package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;
import com.jadencode.main.pluginbuilder.items.ItemWeaponType;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class WeaponTypeEditor extends ContentEditor<ItemWeaponType> {

    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> scriptSelection;
    private final JTextField weightField;
    private final JTable partsList;

    public WeaponTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.statSetSelection = this.create(new JComboBox<>(), 10, 140, 200, 18);
        this.scriptSelection = this.create(new JComboBox<>(), 10, 160, 200, 18);
        this.weightField = this.create(new JTextField(), 10, 180, 200, 18);
        this.partsList = this.create(new JTable(), 10, 200, 200, 160);
    }
    @Override
    public void populate(ItemWeaponType item) {
        this.statSetSelection.setSelectedItem(item.getStatSetName());
        this.scriptSelection.setSelectedItem(item.getScriptName());
        this.weightField.setText(item.getWeight() + "");

        List<String> partTypes = item.getPartTypes();

        for (int i = 0; i < partTypes.size(); i++) {
            String partType = partTypes.get(i);
            this.partsList.getModel().setValueAt(partType, i, 0);
        }
    }
    @Override
    public void onOpened(Module<ItemWeaponType> parent, PluginBuilderPanel panel) {
        Module statSetModule = panel.getModule("Stat Sets");
        List<String> statSets = statSetModule.getItemKeys();
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        List<String> scripts = this.getScripts("weapons", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = partTypesModule.getItemKeys();
        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        this.partsList.setModel(new DefaultTableModel(partTypes.size(), 1));
        this.partsList.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }
    @Override
    public ItemWeaponType createItem(String name) {
        String stat = (String)this.statSetSelection.getSelectedItem();
        String script = (String)this.scriptSelection.getSelectedItem();
        float weight = this.getFloat(this.weightField);

        List<String> parts = new ArrayList<>();
        int rows = this.partsList.getModel().getRowCount();
        for(int row = 0; row < rows; row++) {
            String part = (String) this.partsList.getValueAt(row, 0);
            if(part != null && !part.isEmpty()) {
                parts.add(part);
            }
        }

        return new ItemWeaponType(name, stat, script, weight, parts);
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