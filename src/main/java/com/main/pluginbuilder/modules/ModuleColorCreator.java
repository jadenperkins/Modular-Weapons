package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectColor;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ColorEditor;
import com.main.pluginbuilder.contenteditors.ContentEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleColorCreator extends Module<PipelineObjectColor> {
    private final ContentEditor<PipelineObjectColor> contentEditor;
    public ModuleColorCreator(PluginBuilderPanel parent) {
        super("Colors", Plugin::getColors);
        this.contentEditor = new ColorEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectColor> getContentEditor() {
        return this.contentEditor;
    }
}