package com.jadencode.main.pluginbuilder.contenteditors;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemPartType;
import com.jadencode.main.pluginbuilder.items.ItemStat;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatEditor extends ContentEditor<ItemStat> {

    private final JTextField scriptName;
    private final JTextField defaultValue;

    public StatEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.scriptName = this.create(new JTextField(), 10, 120, 200, 20);
        this.defaultValue = this.create(new JTextField(), 10, 150, 200, 20);
    }
    @Override
    public void populate(ItemStat item) {
        this.scriptName.setText(item.getScriptName());
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
        return new ItemStat(name, this.scriptName.getText(), value);
    }
    @Override
    public ItemStat getDefault() {
        return new ItemStat("", "", 0.0);
    }
}