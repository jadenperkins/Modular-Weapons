package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemStat;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatEditor extends ContentEditor<ItemStat> {

    private final JComboBox<String> scriptSelection;
    private final JTextField defaultValue;

    public StatEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.scriptSelection = this.create(new JComboBox<>(), 10, 120, 200, 20);
        this.defaultValue = this.create(new JTextField(), 10, 150, 200, 20);
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
    public ItemStat createItem(String name) {
        double value;
        try {
            value = Double.parseDouble(this.defaultValue.getText());
        } catch (Exception e) {
            value = 0;
            this.defaultValue.setText("0.0");
        }
        return new ItemStat(name, (String) this.scriptSelection.getSelectedItem(), value);
    }
    @Override
    public ItemStat getDefault() {
        return new ItemStat("", "", 0.0);
    }

    @Override
    public ItemStat consume(String name, JsonObject json) {
        JsonHelper helper = new JsonHelper(json);
        return new ItemStat(name, helper.getString("script"), helper.getDouble("default"));
    }
}