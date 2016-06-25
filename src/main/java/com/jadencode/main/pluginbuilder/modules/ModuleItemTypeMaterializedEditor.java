package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeMaterialized;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeUnique;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemTypeMaterializedEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemTypeUniqueEditor;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ModuleItemTypeMaterializedEditor extends Module<ContentObjectItemTypeMaterialized> {
    private final ContentEditor<ContentObjectItemTypeMaterialized> editor;
    public ModuleItemTypeMaterializedEditor(PluginBuilderPanel panel) {
        super("Materialized Item Types");
        this.editor = new ItemTypeMaterializedEditor(this, panel);
    }

    @Override
    public ContentEditor<ContentObjectItemTypeMaterialized> getContentEditor() {
        return this.editor;
    }
}