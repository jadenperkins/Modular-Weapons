package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.StatSetEditor;
import com.main.plugins.pipeline.PipelineObjectStatSet;

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