package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectMaterialModifier;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.MaterialModifierEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialModifierCreator extends Module<PipelineObjectMaterialModifier> {
    private final ContentEditor<PipelineObjectMaterialModifier> contentEditor;
    public ModuleMaterialModifierCreator(PluginBuilderPanel parent) {
        super("Material Modifiers", Plugin::getMaterialModifiers);
        this.contentEditor = new MaterialModifierEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectMaterialModifier> getContentEditor() {
        return this.contentEditor;
    }
}