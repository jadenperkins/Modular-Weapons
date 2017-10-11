package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.MaterialTypeEditor;
import com.main.pluginbuilder.items.ItemMaterialType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialTypeCreator extends Module<ItemMaterialType> {
    private final ContentEditor<ItemMaterialType> contentEditor;
    public ModuleMaterialTypeCreator(PluginBuilderPanel parent) {
        super("MaterialBase Types");
        this.contentEditor = new MaterialTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemMaterialType> getContentEditor() {
        return this.contentEditor;
    }
}