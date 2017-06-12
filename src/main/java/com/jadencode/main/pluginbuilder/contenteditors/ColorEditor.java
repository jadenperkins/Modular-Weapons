package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectColor;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ColorEditor extends ContentEditor<ContentObjectColor> {

    private final JButton selectColor;
    private final JPanel displayPanel;
    private Color color = Color.WHITE;

    public ColorEditor(PluginBuilderPanel parent) {
        super("Colors", parent);

        GuiHelper helper = GuiHelper.above(this);

        this.displayPanel = helper.add(new JPanel(), "Color Display", H_E, V_S, 500, 500);
        this.displayPanel.setBackground(this.color);

        this.selectColor = helper.add(new JButton("Select Color"), H_S, V_E, H_L, H_BTN);
        this.selectColor.addActionListener(e -> {
            Color c = this.color;
            this.color = JColorChooser.showDialog(null, "Select a Color", this.color);
            if (this.color == null) {
                this.color = c;
            }
            this.displayPanel.setBackground(this.color);
        });
    }

    @Override
    public void populate(ContentObjectColor item) {
        this.color = new Color(item.red, item.green, item.blue);
        this.displayPanel.setBackground(this.color);
    }

    @Override
    public ContentObjectColor createItem(String name, String owner) {
        return new ContentObjectColor(name, owner, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }

    @Override
    public ContentObjectColor getDefault() {
        return new ContentObjectColor("", "", 255, 255, 255);
    }

    @Override
    public ContentObjectColor consume(String name, JsonObject json, String owner) {
        JsonArray rgb = json.get("rgb").getAsJsonArray();
        return new ContentObjectColor(name, owner, rgb.get(0).getAsInt(), rgb.get(1).getAsInt(), rgb.get(2).getAsInt());
    }
}