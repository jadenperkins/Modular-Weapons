package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.IconEditor;
import com.main.plugins.pipeline.PipelineObjectIcon;

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