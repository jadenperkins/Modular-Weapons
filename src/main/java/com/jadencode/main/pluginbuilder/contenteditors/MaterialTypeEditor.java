package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemMaterialType;
import com.jadencode.main.pluginbuilder.items.ItemScript;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.pluginbuilder.modules.ModuleScriptCreator;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class MaterialTypeEditor extends ContentEditor<ItemMaterialType> {

    private final JTextField scriptName;

    public MaterialTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        this.scriptName = this.create(new JTextField(), 10, 120, 200, 20);
    }
    @Override
    public void populate(ItemMaterialType item) {
        this.scriptName.setText(item.getScriptName());
    }
    @Override
    public ItemMaterialType createItem(String name) {
        return new ItemMaterialType(name, this.scriptName.getText());
    }
    @Override
    public ItemMaterialType getDefault() {
        return new ItemMaterialType("", "");
    }

    @Override
    public ItemMaterialType consume(String name, JsonObject json) {
        return new ItemMaterialType(name, json.has("script") ? json.get("script").getAsString() : "");
    }
}