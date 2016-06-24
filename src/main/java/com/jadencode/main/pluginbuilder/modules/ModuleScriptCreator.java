package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ColorEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ScriptEditor;
import com.jadencode.main.pluginbuilder.items.ItemColor;
import com.jadencode.main.pluginbuilder.items.ItemScript;

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