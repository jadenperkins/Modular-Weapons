package com.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.JsonHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.items.ItemStat;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatEditor extends ContentEditor<ItemStat> {

    private final JComboBox<String> scriptSelection;
    private final JTextField defaultValue;

    public StatEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E, H_L, H_FLD);
        this.defaultValue = helper.add(new JTextField(), "Default Value", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<ItemStat> parent, PluginBuilderPanel panel) {
        List<String> scripts = this.getScripts("stats", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }
    @Override
    public void populate(ItemStat item) {
        this.scriptSelection.setSelectedItem(item.getScriptName());
        this.defaultValue.setText(item.getDefaultValue() + "");
    }
    @Override
    public ItemStat createItem(String name, String owner) {
        double value;
        try {
            value = Double.parseDouble(this.defaultValue.getText());
        } catch (Exception e) {
            value = 0;
            this.defaultValue.setText("0.0");
        }
        return new ItemStat(name, owner, (String) this.scriptSelection.getSelectedItem(), value);
    }
    @Override
    public ItemStat getDefault() {
        return new ItemStat("", "", "", 0.0);
    }

    @Override
    public ItemStat consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        return new ItemStat(name, owner, helper.getString("script"), helper.getDouble("default"));
    }
}