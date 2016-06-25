package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ScriptEditor;
import com.jadencode.main.pluginbuilder.content.ContentObjectScript;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleScriptCreator extends Module<ContentObjectScript> {
    private final ContentEditor<ContentObjectScript> contentEditor;
    public ModuleScriptCreator(PluginBuilderPanel parent) {
        super("Scripts");
        this.contentEditor = new ScriptEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectScript> getContentEditor() {
        return this.contentEditor;
    }
}