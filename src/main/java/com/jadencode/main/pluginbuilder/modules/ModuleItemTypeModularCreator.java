package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ItemTypeModularEditor;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeModular;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleItemTypeModularCreator extends Module<ContentObjectItemTypeModular> {
    private final ContentEditor<ContentObjectItemTypeModular> contentEditor;
    public ModuleItemTypeModularCreator(PluginBuilderPanel parent) {
        super("Modular Item Types");
        this.contentEditor = new ItemTypeModularEditor(this, parent);
    }
    @Override
    public ContentEditor<ContentObjectItemTypeModular> getContentEditor() {
        return this.contentEditor;
    }
}