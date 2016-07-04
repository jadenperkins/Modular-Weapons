package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectMaterialModifier;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialModifierEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialModifierCreator extends Module<ContentObjectMaterialModifier> {
    private final ContentEditor<ContentObjectMaterialModifier> contentEditor;

    public ModuleMaterialModifierCreator(PluginBuilderPanel parent) {
        super("Material Modifiers");
        this.contentEditor = new MaterialModifierEditor(this, parent);
    }

    @Override
    public ContentEditor<ContentObjectMaterialModifier> getContentEditor() {
        return this.contentEditor;
    }
}