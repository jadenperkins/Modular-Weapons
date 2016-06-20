package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterial;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialEditor extends ContentEditor<ItemMaterial> {

    private final JTextField colorName;
    private final JTextField weightField;
    private final JTextField modField;
    private final JTextField levelField;
    private final JTextField materialField;

    public MaterialEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.colorName = this.create(new JTextField(), 10, 140, 200, 18);
        this.weightField = this.create(new JTextField(), 10, 160, 200, 18);
        this.modField = this.create(new JTextField(), 10, 180, 200, 18);
        this.levelField = this.create(new JTextField(), 10, 200, 200, 18);
        this.materialField = this.create(new JTextField(), 10, 220, 200, 18);
    }
    @Override
    public void populate(ItemMaterial item) {
        this.colorName.setText(item.getColorName());
        this.weightField.setText(item.getWeight() + "");
        this.modField.setText(item.getMod() + "");
        this.levelField.setText(item.getLevel() + "");
        this.materialField.setText(item.getMaterialType());
    }
    @Override
    public ItemMaterial createItem(String name) {
        String colorName = this.colorName.getText();
        float weight = this.getValue(this.weightField);
        float mod = this.getValue(this.modField);
        int level = this.getInt(this.levelField);
        String material = this.materialField.getText();

        return new ItemMaterial(name, colorName, weight, mod, level, material);
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
    private int getInt(JTextField field) {
        int value;
        try {
            value = Integer.parseInt(field.getText());
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }
    @Override
    public ItemMaterial getDefault() {
        return new ItemMaterial("", "", 0F, 0F, 0, "");
    }

    @Override
    public ItemMaterial consume(String name, JsonObject json) {
        String colorName = json.get("color").getAsString();
        float w = json.has("weight") ? json.get("weight").getAsFloat() : 1F;
        float m = json.get("mod").getAsFloat();
        int l = json.get("level").getAsInt();
        String material = json.get("material").getAsString();

        return new ItemMaterial(name, colorName, w, m, l, material);
    }
}