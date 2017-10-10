package com.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.JsonHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.items.ItemPartType;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PartTypeEditor extends ContentEditor<ItemPartType> {

    private final JComboBox<String> iconSelection;

    public PartTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<ItemPartType> parent, PluginBuilderPanel panel) {
        Module iconModules = panel.getModule("Icons");
        List<String> icons = iconModules.getItemKeys();
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));
    }
    @Override
    public void populate(ItemPartType item) {
        this.iconSelection.setSelectedItem(item.getIconName());
    }
    @Override
    public ItemPartType createItem(String name, String owner) {
        return new ItemPartType(name, owner, (String)this.iconSelection.getSelectedItem());
    }
    @Override
    public ItemPartType getDefault() {
        return new ItemPartType("", "", "");
    }

    @Override
    public ItemPartType consume(String name, JsonObject json, String owner) {
        return new ItemPartType(name, owner, new JsonHelper(json).getString("icon"));
    }
}