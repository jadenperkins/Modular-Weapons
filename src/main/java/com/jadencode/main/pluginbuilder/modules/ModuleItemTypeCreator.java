package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemTypeEditor;
import com.jadencode.main.pluginbuilder.items.ItemItemType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleItemTypeCreator extends Module<ItemItemType> {
    private final ContentEditor<ItemItemType> contentEditor;
    public ModuleItemTypeCreator(PluginBuilderPanel parent) {
        super("Item Types");
        this.contentEditor = new ItemTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemItemType> getContentEditor() {
        return this.contentEditor;
    }
}