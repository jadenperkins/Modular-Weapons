package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.StatEditor;
import com.main.plugins.pipeline.PipelineObjectStat;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatCreator extends Module<PipelineObjectStat> {
    private final ContentEditor<PipelineObjectStat> contentEditor;
    public ModuleStatCreator(PluginBuilderPanel parent) {
        super("Stats", Plugin::getStats);
        this.contentEditor = new StatEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectStat> getContentEditor() {
        return this.contentEditor;
    }
}