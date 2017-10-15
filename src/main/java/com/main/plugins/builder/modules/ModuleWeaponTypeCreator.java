package com.main.plugins.builder.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.contenteditors.ContentEditor;
import com.main.plugins.builder.contenteditors.WeaponTypeEditor;
import com.main.plugins.pipeline.PipelineObjectWeaponType;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleWeaponTypeCreator extends Module<PipelineObjectWeaponType> {
    private final ContentEditor<PipelineObjectWeaponType> contentEditor;
    public ModuleWeaponTypeCreator(PluginBuilderPanel parent) {
        super("Weapon Types", Plugin::getWeaponTypes);
        this.contentEditor = new WeaponTypeEditor(this, parent);
    }
    @Override
    public ContentEditor<PipelineObjectWeaponType> getContentEditor() {
        return this.contentEditor;
    }
}