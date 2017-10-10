package com.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.main.pluginbuilder.GuiHelper;
import com.main.pluginbuilder.PluginBuilderPanel;
import com.main.pluginbuilder.items.ItemColor;
import com.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ColorEditor extends ContentEditor<ItemColor> {

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
    public void populate(ItemColor item) {
        this.color = new Color(item.red, item.green, item.blue);
        this.displayPanel.setBackground(this.color);
    }
    @Override
    public ItemColor createItem(String name, String owner) {
        return new ItemColor(name, owner, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }
    @Override
    public ItemColor getDefault() {
        return new ItemColor("", "", 255, 255, 255);
    }

    @Override
    public ItemColor consume(String name, JsonObject json, String owner) {
        JsonArray rgb = json.get("rgb").getAsJsonArray();
        return new ItemColor(name, owner, rgb.get(0).getAsInt(), rgb.get(1).getAsInt(), rgb.get(2).getAsInt());
    }
}