package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectWeaponType;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.contenteditors.ContentEditor;
import com.main.pluginbuilder.contenteditors.WeaponTypeEditor;

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