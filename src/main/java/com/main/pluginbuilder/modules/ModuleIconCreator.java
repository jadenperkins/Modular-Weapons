package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.IconEditor;
import com.main.pluginbuilder.items.ItemIcon;

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
}