package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectStatSet;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.StatSetEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatSetCreator extends Module<PipelineObjectStatSet> {
    private final ContentEditor<PipelineObjectStatSet> contentEditor;
    public ModuleStatSetCreator(PluginBuilderPanel parent) {
        super("Stat Sets", Plugin::getStatSets);
        this.contentEditor = new StatSetEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectStatSet> getContentEditor() {
        return this.contentEditor;
    }
}