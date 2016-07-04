package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectIcon;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.IconEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleIconCreator extends Module<ContentObjectIcon> {
    private final ContentEditor<ContentObjectIcon> contentEditor;

    public ModuleIconCreator(PluginBuilderPanel parent) {
        super("Icons");
        this.contentEditor = new IconEditor(this, parent);
    }

    @Override
    public ContentEditor<ContentObjectIcon> getContentEditor() {
        return this.contentEditor;
    }
}