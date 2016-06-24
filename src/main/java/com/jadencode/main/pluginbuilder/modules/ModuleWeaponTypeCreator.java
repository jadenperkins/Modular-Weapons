package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.StatSetEditor;
import com.jadencode.main.pluginbuilder.contenteditors.WeaponTypeEditor;
import com.jadencode.main.pluginbuilder.items.ItemStatSet;
import com.jadencode.main.pluginbuilder.items.ItemWeaponType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleWeaponTypeCreator extends Module<ItemWeaponType> {
    private final ContentEditor<ItemWeaponType> contentEditor;
    public ModuleWeaponTypeCreator(PluginBuilderPanel parent) {
        super("Weapon Types");
        this.contentEditor = new WeaponTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<ItemWeaponType> getContentEditor() {
        return this.contentEditor;
    }
}