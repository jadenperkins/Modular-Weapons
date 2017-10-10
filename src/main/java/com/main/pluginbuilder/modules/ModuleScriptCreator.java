package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.ScriptEditor;
import com.main.pluginbuilder.items.ItemScript;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleScriptCreator extends Module<ItemScript> {
    private final ContentEditor<ItemScript> contentEditor;
    public ModuleScriptCreator(PluginBuilderPanel parent) {
        super("Scripts");
        this.contentEditor = new ScriptEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemScript> getContentEditor() {
        return this.contentEditor;
    }
}