package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialTypeEditor;
import com.jadencode.main.pluginbuilder.contenteditors.PartTypeEditor;
import com.jadencode.main.pluginbuilder.items.ItemMaterialType;
import com.jadencode.main.pluginbuilder.items.ItemPartType;

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