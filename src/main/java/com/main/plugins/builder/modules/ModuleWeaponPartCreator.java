package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.WeaponPartEditor;
import com.main.plugins.pipeline.PipelineObjectWeaponPart;

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