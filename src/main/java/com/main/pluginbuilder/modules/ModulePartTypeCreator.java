package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectPartType;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.PartTypeEditor;

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