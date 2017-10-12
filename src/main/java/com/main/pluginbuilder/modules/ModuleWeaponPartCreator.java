package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectWeaponPart;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.WeaponPartEditor;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleWeaponPartCreator extends Module<PipelineObjectWeaponPart> {
    private final ContentEditor<PipelineObjectWeaponPart> contentEditor;
    public ModuleWeaponPartCreator(PluginBuilderPanel parent) {
        super("Weapon Parts", Plugin::getWeaponParts);
        this.contentEditor = new WeaponPartEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectWeaponPart> getContentEditor() {
        return this.contentEditor;
    }
}