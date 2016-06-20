package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.StatEditor;
import com.jadencode.main.pluginbuilder.contenteditors.StatSetEditor;
import com.jadencode.main.pluginbuilder.items.ItemStat;
import com.jadencode.main.pluginbuilder.items.ItemStatSet;

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