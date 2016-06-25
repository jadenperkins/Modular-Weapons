package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialEditor;
import com.jadencode.main.pluginbuilder.content.ContentObjectMaterial;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialCreator extends Module<ContentObjectMaterial> {
    private final ContentEditor<ContentObjectMaterial> contentEditor;
    public ModuleMaterialCreator(PluginBuilderPanel parent) {
        super("Materials");
        this.contentEditor = new MaterialEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectMaterial> getContentEditor() {
        return this.contentEditor;
    }
}