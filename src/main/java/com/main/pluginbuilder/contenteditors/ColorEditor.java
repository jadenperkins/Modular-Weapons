package com.main.pluginbuilder.contenteditors;

import com.main.pipeline.PipelineObjectColor;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ColorEditor extends ContentEditor<PipelineObjectColor> {

    private final JButton selectColor;
    private Color color = Color.WHITE;
    private final JPanel displayPanel;
    public ColorEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);

        GuiHelper helper = GuiHelper.above(this);

        this.displayPanel = helper.add(new JPanel(), "Color Display", H_E, V_S, 500, 500);
        this.displayPanel.setBackground(this.color);

        this.selectColor = helper.add(new JButton("Select Color"), H_S, V_E, H_L, H_BTN);
        this.selectColor.addActionListener(e -> {
            Color c = this.color;
            this.color = JColorChooser.showDialog(null, "Select a Color", this.color);
            if(this.color == null) {
                this.color = c;
            }
            this.displayPanel.setBackground(this.color);
        });
    }
    @Override
    public void populate(PipelineObjectColor item) {
        this.color = new Color(item.getRed(), item.getGreen(), item.getBlue());
        this.displayPanel.setBackground(this.color);
    }
    @Override
    public PipelineObjectColor createItem(String name) {
        return new PipelineObjectColor(name, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }
    @Override
    public PipelineObjectColor getDefault() {
        return new PipelineObjectColor("", 255, 255, 255);
    }
}