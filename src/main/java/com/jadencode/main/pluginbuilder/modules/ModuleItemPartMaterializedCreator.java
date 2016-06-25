package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemPartMaterialized;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemPartMaterializedEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleItemPartMaterializedCreator extends Module<ContentObjectItemPartMaterialized> {
    private final ContentEditor<ContentObjectItemPartMaterialized> contentEditor;
    public ModuleItemPartMaterializedCreator(PluginBuilderPanel parent) {
        super("Materialized Item Parts");
        this.contentEditor = new ItemPartMaterializedEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectItemPartMaterialized> getContentEditor() {
        return this.contentEditor;
    }
}