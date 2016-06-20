package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.Item;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public abstract class ContentEditor<T extends Item> extends JPanel {
    private final JTextField nameField;
    private final JButton updateItem;
    private final JButton deleteItem;

    public ContentEditor(Module<T> parent, PluginBuilderPanel panel) {
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocation(430, 10);
        this.setSize(1000, 700);

        this.nameField = this.create(new JTextField(), 10, 10, 200, 18);
        this.updateItem = this.create(new JButton("Update Item"), 10, 30, 200, 40);
        this.deleteItem = this.create(new JButton("Delete Item"), 10, 80, 200, 40);

        this.updateItem.addActionListener(e -> {
            String itemName = this.nameField.getText();
            parent.addItem(itemName);
            panel.updateCurrentObjects(itemName);
        });
        this.deleteItem.addActionListener(e -> {
            String itemName = this.nameField.getText();
            parent.remove(itemName);
            panel.updateCurrentObjects(null);
        });
    }
    public void onOpened(Module<T> parent, PluginBuilderPanel panel) {

    }
    public void setName(String name) {
        this.nameField.setText(name);
    }
    public <T extends JComponent> T create(T component, int x, int y, int w, int h) {
        component.setLocation(x, y);
        component.setSize(w, h);
        this.add(component);
        return component;
    }
    public abstract T createItem(String name);
    public abstract void populate(T item);
    public abstract T getDefault();
    public abstract T consume(String name, JsonObject json);
}
