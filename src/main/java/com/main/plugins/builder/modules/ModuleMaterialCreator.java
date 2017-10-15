package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.MaterialEditor;
import com.main.plugins.pipeline.PipelineObjectMaterial;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialCreator extends Module<PipelineObjectMaterial> {
    private final ContentEditor<PipelineObjectMaterial> contentEditor;
    public ModuleMaterialCreator(PluginBuilderPanel parent) {
        super("Materials", Plugin::getMaterials);
        this.contentEditor = new MaterialEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectMaterial> getContentEditor() {
        return this.contentEditor;
    }
}