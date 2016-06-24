package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.contenteditors.ColorEditor;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleColorCreator extends Module<ItemColor> {
    private final ContentEditor<ItemColor> contentEditor;
    public ModuleColorCreator(PluginBuilderPanel parent) {
        super("Colors");
        this.contentEditor = new ColorEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemColor> getContentEditor() {
        return this.contentEditor;
    }
}