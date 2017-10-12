package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectStat;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.StatEditor;

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