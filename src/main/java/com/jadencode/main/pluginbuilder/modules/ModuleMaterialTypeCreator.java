package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialTypeEditor;
import com.jadencode.main.pluginbuilder.content.ContentObjectMaterialType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialTypeCreator extends Module<ContentObjectMaterialType> {
    private final ContentEditor<ContentObjectMaterialType> contentEditor;
    public ModuleMaterialTypeCreator(PluginBuilderPanel parent) {
        super("Material Types");
        this.contentEditor = new MaterialTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectMaterialType> getContentEditor() {
        return this.contentEditor;
    }
}