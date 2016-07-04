package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectPartType;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.PartTypeEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModulePartTypeCreator extends Module<ContentObjectPartType> {
    private final ContentEditor<ContentObjectPartType> contentEditor;

    public ModulePartTypeCreator(PluginBuilderPanel parent) {
        super("Part Types");
        this.contentEditor = new PartTypeEditor(this, parent);
    }

    @Override
    public ContentEditor<ContentObjectPartType> getContentEditor() {
        return this.contentEditor;
    }
}