package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ColorEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.IconEditor;
import com.jadencode.main.pluginbuilder.items.ItemColor;
import com.jadencode.main.pluginbuilder.items.ItemIcon;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleIconCreator extends Module<ItemIcon> {
    private final ContentEditor<ItemIcon> contentEditor;
    public ModuleIconCreator(PluginBuilderPanel parent) {
        super("Icons");
        this.contentEditor = new IconEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemIcon> getContentEditor() {
        return this.contentEditor;
    }
    @Override
    public ItemIcon createItem(String name) {
        ItemIcon item = new ItemIcon(name, 255, 255, 255);
        return item;
    }
}