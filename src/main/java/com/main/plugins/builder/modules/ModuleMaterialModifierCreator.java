package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.MaterialModifierEditor;
import com.main.plugins.pipeline.PipelineObjectMaterialModifier;

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