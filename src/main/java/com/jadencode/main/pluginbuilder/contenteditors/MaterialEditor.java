package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterial;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialEditor extends ContentEditor<ItemMaterial> {

    private final JComboBox<String> colorSelection;
    private final JTextField weightField;
    private final JTextField modField;
    private final JTextField levelField;
    private final JComboBox<String> materialSelection;

    public MaterialEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.colorSelection = helper.add(new JComboBox<>(), "Color", H_S, V_E, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.modField = helper.add(new JTextField(), "Modifier", H_S, V_E + 2 * H_FLD + 2 * V_PAD, H_L, H_FLD);
        this.levelField = helper.add(new JTextField(), "Level", H_S, V_E + 3 * H_FLD + 3 * V_PAD, H_L, H_FLD);
        this.materialSelection = helper.add(new JComboBox<>(), "Material Type", H_S, V_E + 4 * H_FLD + 4 * V_PAD, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<ItemMaterial> parent, PluginBuilderPanel panel) {
        Module colorModule = panel.getModule("Colors");
        List<String> colors = colorModule.getItemKeys();
        this.colorSelection.setModel(new DefaultComboBoxModel<>(colors.toArray(new String[0])));

        Module materalTypesModule = panel.getModule("Material Types");
        List<String> types = materalTypesModule.getItemKeys();
        this.materialSelection.setModel(new DefaultComboBoxModel<>(types.toArray(new String[0])));
    }
    @Override
    public void populate(ItemMaterial item) {
        this.colorSelection.setSelectedItem(item.getColorName());
        this.weightField.setText(item.getWeight() + "");
        this.modField.setText(item.getMod() + "");
        this.levelField.setText(item.getLevel() + "");
        this.materialSelection.setSelectedItem(item.getMaterialType());
    }
    @Override
    public ItemMaterial createItem(String name, String owner) {
        String colorName = (String)this.colorSelection.getSelectedItem();
        float weight = this.getValue(this.weightField);
        float mod = this.getValue(this.modField);
        int level = this.getInt(this.levelField);
        String material = (String)this.materialSelection.getSelectedItem();

        return new ItemMaterial(name, owner, colorName, weight, mod, level, material);
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
        return new ItemMaterial("", "", "", 0F, 0F, 0, "");
    }

    @Override
    public ItemMaterial consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String colorName = helper.getString("color");
        float w = helper.getFloat("weight", 1F);
        float m = helper.getFloat("mod", 1F);
        int l = helper.getInt("level");
        String material = helper.getString("material");
        return new ItemMaterial(name, owner, colorName, w, m, l, material);
    }
}