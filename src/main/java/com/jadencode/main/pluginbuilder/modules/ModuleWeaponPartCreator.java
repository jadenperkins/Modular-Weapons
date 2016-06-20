package com.jadencode.main.pluginbuilder.modules;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.contenteditors.ContentEditor;
import com.jadencode.main.pluginbuilder.contenteditors.MaterialModifierEditor;
import com.jadencode.main.pluginbuilder.contenteditors.WeaponPartEditor;
import com.jadencode.main.pluginbuilder.items.ItemMaterialModifier;
import com.jadencode.main.pluginbuilder.items.ItemWeaponPart;

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