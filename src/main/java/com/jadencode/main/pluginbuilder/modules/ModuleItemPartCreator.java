package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemPartEditor;
import com.jadencode.main.pluginbuilder.items.ItemItemPart;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleItemPartCreator extends Module<ItemItemPart> {
    private final ContentEditor<ItemItemPart> contentEditor;
    public ModuleItemPartCreator(PluginBuilderPanel parent) {
        super("Item Parts");
        this.contentEditor = new ItemPartEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemItemPart> getContentEditor() {
        return this.contentEditor;
    }
}