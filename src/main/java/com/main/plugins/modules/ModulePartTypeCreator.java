package com.main.plugins.modules;

import com.main.constants.Icons;
import com.main.constants.PartTypes;
import com.main.generate.weapon.WeaponPartType;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.pipeline.PipelineObjectPartType;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModulePartTypeCreator extends Module<PipelineObjectPartType> {

    private static final String ICON_SELECTION = "iconSelection";

    public ModulePartTypeCreator() {
        super("Part Types", 1, Plugin::getPartTypes);
    }

    @Override
    public PipelineObjectPartType createItem(ContentEditor<PipelineObjectPartType> contentEditor, String name) {
        return new PipelineObjectPartType(name, (String) contentEditor.getComponent(ICON_SELECTION).getAsComboBox().getSelectedItem());
    }

    @Override
    public void populatePanel(ContentEditor<PipelineObjectPartType> contentEditor) {
        contentEditor.addComponent(ICON_SELECTION, new BuilderComboBox<>(), H_S, V_E, H_L, H_FLD);
    }

    @Override
    public void loadObject(ContentEditor<PipelineObjectPartType> contentEditor, PipelineObjectPartType pipelineObject) {
        contentEditor.getComponent(ICON_SELECTION).getAsComboBox().setSelectedItem(pipelineObject.getIcon());
    }

    @Override
    public void onOpened(ContentEditor<PipelineObjectPartType> contentEditor, PluginBuilderPanel panel) {
        Module iconsModule = panel.getModule("Icons");
        List<String> icons = iconsModule.getItemKeys();
        contentEditor.getComponent(ICON_SELECTION).getAsComboBox().setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));
    }
    @Override
    public PipelineObjectPartType getDefault() {
        return new PipelineObjectPartType("", "");
    }

    @Override
    public void consume(PipelineObjectPartType obj) {
        String iconName = obj.getIcon();
        BufferedImage icon = Icons.get(iconName);
        PartTypes.register(new WeaponPartType(obj.getName(), icon));
    }
}