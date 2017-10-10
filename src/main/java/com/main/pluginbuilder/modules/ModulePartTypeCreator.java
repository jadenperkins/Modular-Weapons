package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.PartTypeEditor;
import com.main.pluginbuilder.items.ItemPartType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModulePartTypeCreator extends Module<ItemPartType> {
    private final ContentEditor<ItemPartType> contentEditor;
    public ModulePartTypeCreator(PluginBuilderPanel parent) {
        super("Part Types");
        this.contentEditor = new PartTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemPartType> getContentEditor() {
        return this.contentEditor;
    }
}