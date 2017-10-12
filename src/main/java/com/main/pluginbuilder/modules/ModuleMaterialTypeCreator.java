package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectMaterialType;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.MaterialTypeEditor;

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