package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.MaterialEditor;
import com.main.pluginbuilder.items.ItemMaterial;

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