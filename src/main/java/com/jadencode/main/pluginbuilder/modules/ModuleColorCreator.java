package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectColor;
import com.jadencode.main.pluginbuilder.contenteditors.ColorEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleColorCreator extends Module<ContentObjectColor> {
    private final ContentEditor<ContentObjectColor> contentEditor;

    public ModuleColorCreator(PluginBuilderPanel parent) {
        super("Colors");
        this.contentEditor = new ColorEditor(this, parent);
    }

    @Override
    public ContentEditor<ContentObjectColor> getContentEditor() {
        return this.contentEditor;
    }
}