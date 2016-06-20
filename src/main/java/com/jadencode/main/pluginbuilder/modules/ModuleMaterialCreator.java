package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialModifierEditor;
import com.jadencode.main.pluginbuilder.items.ItemMaterial;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialCreator extends Module<ItemMaterial> {
    private final ContentEditor<ItemMaterial> contentEditor;
    public ModuleMaterialCreator(PluginBuilderPanel parent) {
        super("Materials");
        this.contentEditor = new MaterialEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemMaterial> getContentEditor() {
        return this.contentEditor;
    }
}