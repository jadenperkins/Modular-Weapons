package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.MaterialModifierEditor;
import com.main.pluginbuilder.items.ItemMaterialModifier;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialModifierCreator extends Module<ItemMaterialModifier> {
    private final ContentEditor<ItemMaterialModifier> contentEditor;
    public ModuleMaterialModifierCreator(PluginBuilderPanel parent) {
        super("Material Modifiers");
        this.contentEditor = new MaterialModifierEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemMaterialModifier> getContentEditor() {
        return this.contentEditor;
    }
}