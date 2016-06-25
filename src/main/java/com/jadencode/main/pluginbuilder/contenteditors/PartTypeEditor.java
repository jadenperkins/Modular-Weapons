package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.util.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectPartType;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PartTypeEditor extends ContentEditor<ContentObjectPartType> {

    private final JComboBox<String> iconSelection;

    public PartTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E, H_L, H_FLD);
    }
    @Override
    public void onOpened(Module<ContentObjectPartType> parent, PluginBuilderPanel panel) {
        Module iconModules = panel.getModule("Icons");
        List<String> icons = iconModules.getItemKeys();
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));
    }
    @Override
    public void populate(ContentObjectPartType item) {
        this.iconSelection.setSelectedItem(item.getIconName());
    }
    @Override
    public ContentObjectPartType createItem(String name, String owner) {
        return new ContentObjectPartType(name, owner, (String)this.iconSelection.getSelectedItem());
    }
    @Override
    public ContentObjectPartType getDefault() {
        return new ContentObjectPartType("", "", "");
    }

    @Override
    public ContentObjectPartType consume(String name, JsonObject json, String owner) {
        return new ContentObjectPartType(name, owner, new JsonHelper(json).getString("icon"));
    }
}