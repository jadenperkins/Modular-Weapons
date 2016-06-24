package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialTypeEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ScriptEditor;
import com.jadencode.main.pluginbuilder.items.ItemMaterialType;
import com.jadencode.main.pluginbuilder.items.ItemScript;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialTypeCreator extends Module<ItemMaterialType> {
    private final ContentEditor<ItemMaterialType> contentEditor;
    public ModuleMaterialTypeCreator(PluginBuilderPanel parent) {
        super("Material Types");
        this.contentEditor = new MaterialTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemMaterialType> getContentEditor() {
        return this.contentEditor;
    }
}