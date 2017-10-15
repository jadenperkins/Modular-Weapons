package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ColorEditor;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.pipeline.PipelineObjectColor;

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