package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.StatEditor;
import com.jadencode.main.pluginbuilder.content.ContentObjectStat;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatCreator extends Module<ContentObjectStat> {
    private final ContentEditor<ContentObjectStat> contentEditor;
    public ModuleStatCreator(PluginBuilderPanel parent) {
        super("Stats");
        this.contentEditor = new StatEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectStat> getContentEditor() {
        return this.contentEditor;
    }
}