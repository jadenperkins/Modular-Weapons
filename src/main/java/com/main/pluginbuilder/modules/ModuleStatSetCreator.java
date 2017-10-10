package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.StatSetEditor;
import com.main.pluginbuilder.items.ItemStatSet;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatSetCreator extends Module<ItemStatSet> {
    private final ContentEditor<ItemStatSet> contentEditor;
    public ModuleStatSetCreator(PluginBuilderPanel parent) {
        super("Stat Sets");
        this.contentEditor = new StatSetEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemStatSet> getContentEditor() {
        return this.contentEditor;
    }
}