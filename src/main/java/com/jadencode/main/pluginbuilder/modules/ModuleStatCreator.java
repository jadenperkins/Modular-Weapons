package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.PartTypeEditor;
import com.jadencode.main.pluginbuilder.contenteditors.StatEditor;
import com.jadencode.main.pluginbuilder.items.ItemPartType;
import com.jadencode.main.pluginbuilder.items.ItemStat;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatCreator extends Module<ItemStat> {
    private final ContentEditor<ItemStat> contentEditor;
    public ModuleStatCreator(PluginBuilderPanel parent) {
        super("Stats");
        this.contentEditor = new StatEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemStat> getContentEditor() {
        return this.contentEditor;
    }
}