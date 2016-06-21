package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialModifierEditor extends ContentEditor<ItemMaterialModifier> {

    private final JComboBox<String> colorSelection;
    private final JTextField weightField;
    private final JTextField levelField;
    private final JTextField modField;
    private final JList<String> materialsList;

    public MaterialModifierEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.colorSelection = helper.add(new JComboBox<>(), "Color", H_S, V_E, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.levelField = helper.add(new JTextField(), "Level Modifier", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.modField = helper.add(new JTextField(), "Modifier", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.materialsList = helper.addScrolling(new JList<>(), "Material Types", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, 10 * H_FLD);
    }
    @Override
    public void onOpened(Module<ItemMaterialModifier> parent, PluginBuilderPanel panel) {
        Module colorModule = panel.getModule("Colors");
        List<String> colors = colorModule.getItemKeys();
        this.colorSelection.setModel(new DefaultComboBoxModel<>(colors.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("Material Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        this.materialsList.setListData(materialTypes.toArray(new String[0]));
        this.materialsList.setSize(200, 18 * Math.max(1, materialTypes.size()));
    }
    @Override
    public void populate(ItemMaterialModifier item) {
        this.colorSelection.setSelectedItem(item.getColorName());
        this.weightField.setText(item.getWeight() + "");
        this.levelField.setText(item.getLevel() + "");
        this.modField.setText(item.getMod() + "");

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
    public ItemMaterialModifier createItem(String name, String owner) {
        String colorName = (String) this.colorSelection.getSelectedItem();
        float weight = this.getValue(this.weightField);
        float level = this.getValue(this.levelField);
        float mod = this.getValue(this.modField);

        List<String> values = this.materialsList.getSelectedValuesList();
        return new ItemMaterialModifier(name, owner, colorName, weight, level, mod, values);
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
        return new ItemMaterialModifier("", "", "", 0F, 0F, 0F, new ArrayList<>());
    }

    @Override
    public ItemMaterialModifier consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String color = helper.getString("color");
        float weight = helper.getFloat("weight", 1F);
        float level = helper.getFloat("level", 1F);
        float mod = helper.getFloat("mod", 1F);
        List<String> types = new ArrayList<>();
        JsonArray array = helper.getArray("materials");
        for (JsonElement jsonElement : array)
            types.add(jsonElement.getAsString());

        return new ItemMaterialModifier(name, owner, color, weight, level, mod, types);
    }
}