package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.StatSetEditor;
import com.jadencode.main.pluginbuilder.content.ContentObjectStatSet;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatSetCreator extends Module<ContentObjectStatSet> {
    private final ContentEditor<ContentObjectStatSet> contentEditor;
    public ModuleStatSetCreator(PluginBuilderPanel parent) {
        super("Stat Sets");
        this.contentEditor = new StatSetEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectStatSet> getContentEditor() {
        return this.contentEditor;
    }
}