package com.jadencode.main.pluginbuilder.contenteditors;

import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemIcon;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class IconEditor extends ContentEditor<ItemIcon> {
    private final JButton selectImage;
    private ImageIcon icon;
    public IconEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);

        this.selectImage = this.create(new JButton("Select Color"), 10, 120, 200, 40);
        this.selectImage.addActionListener(e -> {
//            this.color = JColorChooser.showDialog(null, "Select a Color", this.color);
//            this.displayPanel.setBackground(this.color);
        });
    }
    @Override
    public void populate(ItemIcon item) {
//        this.color = new Color(item.red, item.green, item.blue);
    }

    @Override
    public ItemIcon createItem(String name) {
        return new ItemIcon(name, 0, 0, 0);
    }
}