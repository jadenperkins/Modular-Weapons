package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeUnique;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemTypeUniqueEditor;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ModuleItemTypeUniqueCreator extends Module<ContentObjectItemTypeUnique> {
    private final ContentEditor<ContentObjectItemTypeUnique> editor;

    public ModuleItemTypeUniqueCreator(PluginBuilderPanel panel) {
        super("Unique Item Types");
        this.editor = new ItemTypeUniqueEditor(this, panel);
    }

    @Override
    public ContentEditor<ContentObjectItemTypeUnique> getContentEditor() {
        return this.editor;
    }
}