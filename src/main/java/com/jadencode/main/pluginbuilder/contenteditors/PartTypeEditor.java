package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.item.ItemPart;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterialType;
import com.jadencode.main.pluginbuilder.items.ItemPartType;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PartTypeEditor extends ContentEditor<ItemPartType> {

    private final JTextField iconName;

    public PartTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.iconName = this.create(new JTextField(), 10, 120, 200, 20);
    }
    @Override
    public void populate(ItemPartType item) {
        this.iconName.setText(item.getIconName());
    }
    @Override
    public ItemPartType createItem(String name) {
        return new ItemPartType(name, this.iconName.getText());
    }
    @Override
    public ItemPartType getDefault() {
        return new ItemPartType("", "");
    }

    @Override
    public ItemPartType consume(String name, JsonObject json) {
        return new ItemPartType(name, json.has("icon") ? json.get("icon").getAsString() : "");
    }
}