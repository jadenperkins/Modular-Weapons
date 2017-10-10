package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.contenteditors.ColorEditor;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.items.ItemColor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleColorCreator extends Module<ItemColor> {
    private final ContentEditor<ItemColor> contentEditor;
    public ModuleColorCreator(PluginBuilderPanel parent) {
        super("Colors");
        this.contentEditor = new ColorEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemColor> getContentEditor() {
        return this.contentEditor;
    }
}