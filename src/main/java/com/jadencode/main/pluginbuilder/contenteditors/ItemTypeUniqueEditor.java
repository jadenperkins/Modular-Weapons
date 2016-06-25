package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.util.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeUnique;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemTypeUniqueEditor extends ContentEditor<ContentObjectItemTypeUnique> {

    private final String[] QUALITY_LEVELS = {
            "COMMON", "UNCOMMON", "RARE", "EPIC", "LEGENDARY"
    };

    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> scriptSelection;
    private final JTextField weightField;
    private final JTextField infoField;
    private final JComboBox<String> qualitySelection;

    public ItemTypeUniqueEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E, H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.infoField = helper.add(new JTextField(), "Description", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.qualitySelection = helper.add(new JComboBox<>(), "Quality Level", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
    }
    @Override
    public void populate(ContentObjectItemTypeUnique item) {
        this.statSetSelection.setSelectedItem(item.getStatSetName());
        this.scriptSelection.setSelectedItem(item.getScriptName());
        this.weightField.setText(item.getWeight() + "");
        this.qualitySelection.setSelectedItem(item.getQualityLevel());
        this.infoField.setText(item.getDescription());
    }
    @Override
    public void onOpened(Module<ContentObjectItemTypeUnique> parent, PluginBuilderPanel panel) {
        Module statSetModule = panel.getModule("Stat Sets");
        List<String> statSets = statSetModule.getItemKeys();
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        List<String> scripts = this.getScripts("items", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        this.qualitySelection.setModel(new DefaultComboBoxModel<>(QUALITY_LEVELS));
    }
    @Override
    public ContentObjectItemTypeUnique createItem(String name, String owner) {
        String stat = (String)this.statSetSelection.getSelectedItem();
        String script = (String)this.scriptSelection.getSelectedItem();
        float weight = this.getFloat(this.weightField);
        String quality = (String)this.qualitySelection.getSelectedItem();
        String info = this.infoField.getText();

        return new ContentObjectItemTypeUnique(name, owner, stat, script, weight, quality, info);
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
    public ContentObjectItemTypeUnique getDefault() {
        return new ContentObjectItemTypeUnique("", "", "", "", 1F, "", "");
    }

    @Override
    public ContentObjectItemTypeUnique consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String statSet = helper.getString("stats");
        String script = helper.getString("script");
        float weight = helper.getFloat("weight");
        String quality = helper.getString("quality");
        String info = helper.getString("partInfo");

        return new ContentObjectItemTypeUnique(name, owner, statSet, script, weight, quality, info);
    }
}