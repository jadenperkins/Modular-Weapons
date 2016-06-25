package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.util.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemPartUnique;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemPartUniqueEditor extends ContentEditor<ContentObjectItemPartUnique> {

    private static final String[] QUALITY_LEVELS = {
            "COMMON", "UNCOMMON", "RARE", "EPIC", "LEGENDARY"
    };

    private final JTextField nameModField;
    private final JTextField partInfoField;
    private final JTextField weightField;
    private final JComboBox<String> partTypeSelection;
    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> iconSelection;
    private final JComboBox<String> scriptSelection;
    private final JComboBox<String> qualitySelection;

    public ItemPartUniqueEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.nameModField = helper.add(new JTextField(), "Name Modifier", H_S, V_E, H_L, H_FLD);
        this.partInfoField = helper.add(new JTextField(), "Part Info", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partTypeSelection = helper.add(new JComboBox<>(), "Part Type", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E + 5 * (H_FLD + V_PAD), H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + 6 * (H_FLD + V_PAD), H_L, H_FLD);
        this.qualitySelection = helper.add(new JComboBox<>(), "Quality Level", H_S, V_E + 7 * (H_FLD + V_PAD), H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<ContentObjectItemPartUnique> parent, PluginBuilderPanel panel) {
        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = partTypesModule.getItemKeys();
        this.partTypeSelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        Module statSetsModule = panel.getModule("Stat Sets");
        List<String> statSets = new ArrayList<>();
        statSets.add("");
        statSets.addAll(statSetsModule.getItemKeys());
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        Module iconsModule = panel.getModule("Icons");
        List<String> icons = new ArrayList<>();
        icons.add("");
        icons.addAll(iconsModule.getItemKeys());
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));

        Module scriptsModule = panel.getModule("Scripts");
        List<String> scripts = new ArrayList<>();
        scripts.add("");
        scripts.addAll(scriptsModule.getItemKeys());
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        this.qualitySelection.setModel(new DefaultComboBoxModel<>(QUALITY_LEVELS));
    }
    @Override
    public void populate(ContentObjectItemPartUnique item) {
        this.nameModField.setText(item.getNameMod());
        this.partInfoField.setText(item.getPartInfo());
        this.weightField.setText(item.getWeight() + "");
        this.partTypeSelection.setSelectedItem(item.getPartType());
        this.statSetSelection.setSelectedItem(item.getStatSet());
        this.iconSelection.setSelectedItem(item.getIconName());
        this.qualitySelection.setSelectedItem(item.getQualityLevel());
        this.scriptSelection.setSelectedItem(item.getScript());
    }
    @Override
    public ContentObjectItemPartUnique createItem(String name, String owner) {
        String nameMod = this.nameModField.getText();
        String partInfo = this.partInfoField.getText();
        float weight = this.getValue(this.weightField);
        String partType = (String) this.partTypeSelection.getSelectedItem();
        String statSet = (String) this.statSetSelection.getSelectedItem();
        String iconName = (String) this.iconSelection.getSelectedItem();
        String quality = (String) this.qualitySelection.getSelectedItem();
        String script = (String) this.scriptSelection.getSelectedItem();
        return new ContentObjectItemPartUnique(name, owner, nameMod, partInfo, weight, partType, statSet, iconName, script, quality);
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
    public ContentObjectItemPartUnique getDefault() {
        return new ContentObjectItemPartUnique("", "", "", "", 0F, "", "", "", "", "");
    }

    @Override
    public ContentObjectItemPartUnique consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String nameMod = helper.getString("nameMod");
        String partInfo = helper.getString("partInfo");
        float weight = helper.getFloat("weight", 1F);
        String partType = helper.getString("partType");
        String statSet = helper.getString("stats");
        String iconName = helper.getString("icon");
        String quality = helper.getString("quality");
        String script = helper.getString("script");

        return new ContentObjectItemPartUnique(name, owner, nameMod, partInfo, weight, partType, statSet, iconName, script, quality);
    }
}