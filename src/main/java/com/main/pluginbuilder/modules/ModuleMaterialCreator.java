package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectMaterial;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.MaterialEditor;

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