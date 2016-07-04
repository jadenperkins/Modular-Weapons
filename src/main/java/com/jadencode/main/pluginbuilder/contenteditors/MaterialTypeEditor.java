package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectMaterialType;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialTypeEditor extends ContentEditor<ContentObjectMaterialType> {

    private final JComboBox<String> scriptSelection;

    public MaterialTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E, H_L, H_FLD);
    }

    @Override
    public void onOpened(Module<ContentObjectMaterialType> parent, PluginBuilderPanel panel) {
        List<String> scripts = this.getScripts("material types", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }

    @Override
    public void populate(ContentObjectMaterialType item) {
        this.scriptSelection.setSelectedItem(item.getScriptName());
    }

    @Override
    public ContentObjectMaterialType createItem(String name, String owner) {
        return new ContentObjectMaterialType(name, owner, (String) this.scriptSelection.getSelectedItem());
    }

    @Override
    public ContentObjectMaterialType getDefault() {
        return new ContentObjectMaterialType("", "", "");
    }

    @Override
    public ContentObjectMaterialType consume(String name, JsonObject json, String owner) {
        return new ContentObjectMaterialType(name, owner, new JsonHelper(json).getString("script"));
    }
}