package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.PartTypeEditor;
import com.main.plugins.pipeline.PipelineObjectPartType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModulePartTypeCreator extends Module<PipelineObjectPartType> {
    private final ContentEditor<PipelineObjectPartType> contentEditor;
    public ModulePartTypeCreator(PluginBuilderPanel parent) {
        super("Part Types", Plugin::getPartTypes);
        this.contentEditor = new PartTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectPartType> getContentEditor() {
        return this.contentEditor;
    }
}