package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectIcon;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.IconEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleIconCreator extends Module<PipelineObjectIcon> {
    private final ContentEditor<PipelineObjectIcon> contentEditor;
    public ModuleIconCreator(PluginBuilderPanel parent) {
        super("Icons", Plugin::getIcons);
        this.contentEditor = new IconEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectIcon> getContentEditor() {
        return this.contentEditor;
    }
}