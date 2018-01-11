package com.main.plugins.modules;

import com.main.constants.Colors;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.component.BuilderComponent;
import com.main.plugins.builder.component.BuilderPanel;
import com.main.plugins.pipeline.PipelineObjectColor;

import javax.swing.*;
import java.awt.*;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleColorCreator extends Module<PipelineObjectColor> {
    private static final String DISPLAY_PANEL = "displayPanel";

    public ModuleColorCreator() {
        super("Colors", 2, Plugin::getColors);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectColor> contentEditor) {
        contentEditor.addComponent(DISPLAY_PANEL, new BuilderPanel(), H_E, V_S, 500, 500);

        JButton button = new JButton("Select Color");
        button.setLocation(H_S, V_E);
        button.setSize(H_L, H_BTN);
        button.addActionListener(event -> {
            BuilderComponent panel = contentEditor.getComponent(DISPLAY_PANEL);
            Color color = JColorChooser.showDialog(null, "Select a Color", panel.getAsPanel().getBackground());
            if (color == null) return;
            panel.getAsPanel().setBackground(color);
        });
        contentEditor.add(button);
    }

    @Override
    public void loadObject(ContentEditor<PipelineObjectColor> contentEditor, PipelineObjectColor item) {
        contentEditor.getComponent(DISPLAY_PANEL).getAsPanel().setBackground(item.makeColor());
    }
    @Override
    public PipelineObjectColor createItem(ContentEditor<PipelineObjectColor> contentEditor, String name) {
        Color color = contentEditor.getComponent(DISPLAY_PANEL).getAsPanel().getBackground();
        return new PipelineObjectColor(name, color);
    }
    @Override
    public PipelineObjectColor getDefault() {
        return new PipelineObjectColor("", Color.WHITE);
    }

    @Override
    public void consume(PipelineObjectColor color) {
        Color c = color.makeColor();
        Colors.register(color.getName(), c);
    }
}