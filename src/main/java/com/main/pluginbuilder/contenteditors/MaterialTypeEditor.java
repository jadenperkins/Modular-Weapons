package com.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.JsonHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.items.ItemMaterialType;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialTypeEditor extends ContentEditor<ItemMaterialType> {

    private final JComboBox<String> scriptSelection;

    public MaterialTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<ItemMaterialType> parent, PluginBuilderPanel panel) {
        List<String> scripts = this.getScripts("material types", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }
    @Override
    public void populate(ItemMaterialType item) {
        this.scriptSelection.setSelectedItem(item.getScriptName());
    }
    @Override
    public ItemMaterialType createItem(String name, String owner) {
        return new ItemMaterialType(name, owner, (String)this.scriptSelection.getSelectedItem());
    }
    @Override
    public ItemMaterialType getDefault() {
        return new ItemMaterialType("", "", "");
    }

    @Override
    public ItemMaterialType consume(String name, JsonObject json, String owner) {
        return new ItemMaterialType(name, owner, new JsonHelper(json).getString("script"));
    }
}