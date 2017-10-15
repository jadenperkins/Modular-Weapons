package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.ScriptEditor;
import com.main.plugins.pipeline.PipelineObjectScript;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleScriptCreator extends Module<PipelineObjectScript> {
    private final ContentEditor<PipelineObjectScript> contentEditor;
    public ModuleScriptCreator(PluginBuilderPanel parent) {
        super("Scripts", Plugin::getScripts);
        this.contentEditor = new ScriptEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectScript> getContentEditor() {
        return this.contentEditor;
    }
}