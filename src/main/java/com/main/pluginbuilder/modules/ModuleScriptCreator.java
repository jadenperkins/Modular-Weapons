package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectScript;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.ScriptEditor;

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