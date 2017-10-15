package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.MaterialTypeEditor;
import com.main.plugins.pipeline.PipelineObjectMaterialType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleMaterialTypeCreator extends Module<PipelineObjectMaterialType> {
    private final ContentEditor<PipelineObjectMaterialType> contentEditor;
    public ModuleMaterialTypeCreator(PluginBuilderPanel parent) {
        super("Material Types", Plugin::getMaterialTypes);
        this.contentEditor = new MaterialTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectMaterialType> getContentEditor() {
        return this.contentEditor;
    }
}