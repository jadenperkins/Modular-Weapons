package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemPartUnique;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemPartUniqueEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleItemPartUniqueCreator extends Module<ContentObjectItemPartUnique> {
    private final ContentEditor<ContentObjectItemPartUnique> contentEditor;

    public ModuleItemPartUniqueCreator(PluginBuilderPanel parent) {
        super("Unique Item Parts");
        this.contentEditor = new ItemPartUniqueEditor(this, parent);
    }

    @Override
    public ContentEditor<ContentObjectItemPartUnique> getContentEditor() {
        return this.contentEditor;
    }
}