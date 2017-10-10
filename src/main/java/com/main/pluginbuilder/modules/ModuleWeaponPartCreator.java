package com.main.pluginbuilder.modules;

import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.WeaponPartEditor;
import com.main.pluginbuilder.items.ItemWeaponPart;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleWeaponPartCreator extends Module<ItemWeaponPart> {
    private final ContentEditor<ItemWeaponPart> contentEditor;
    public ModuleWeaponPartCreator(PluginBuilderPanel parent) {
        super("Weapon Parts");
        this.contentEditor = new WeaponPartEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemWeaponPart> getContentEditor() {
        return this.contentEditor;
    }
}