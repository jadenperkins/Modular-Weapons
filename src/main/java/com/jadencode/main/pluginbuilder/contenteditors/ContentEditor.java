package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObject;
import com.jadencode.main.pluginbuilder.content.ContentObjectScript;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public abstract class ContentEditor<T extends ContentObject> extends JPanel {

    public static final int H_BTN = 40;
    public static final int H_FLD = 20;
    public static final int H_NTR = 16;
    public static final int V_PAD = 10;
    public static final int H_PAD = 20;

    public static final int H_S = 100;
    public static final int H_E = H_S + 200 + H_PAD;
    public static final int V_S = 10;
    public static final int V_E = V_S + H_FLD + 2 * H_BTN + 3 * V_PAD;

    public static final int H_L = H_E - H_S - H_PAD;
    public static final int V_L = V_E - V_S;

    private final JTextField nameField;
    private final JButton updateItem;
    private final JButton deleteItem;

    public ContentEditor(Module<T> parent, PluginBuilderPanel panel) {
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocation(430, 10);
        this.setSize(1000, 700);

        GuiHelper helper = GuiHelper.left(this);

        //Components take up the area from (0, 0) to (300, 130)
        //Editors should place components outside of this range
        this.nameField = helper.add(new JTextField(), "Item Name", H_S, V_S, H_L, H_FLD);
        this.updateItem = helper.add(new JButton("Update Item"), H_S, V_S + H_FLD + V_PAD, H_L, H_BTN);
        this.deleteItem = helper.add(new JButton("Delete Item"), H_S, V_S + H_FLD + H_BTN + 2 * V_PAD, H_L, H_BTN);

        this.updateItem.addActionListener(e -> {
            if(panel.getActivePlugin() == null || panel.getActivePlugin().isEmpty()) {
                JOptionPane.showMessageDialog(null, "An active plugin must be named before adding content!");
            } else {
                String itemName = this.nameField.getText();
                if(itemName != null && !itemName.isEmpty()) {
                    parent.addItem(itemName, panel.getActivePlugin());
                    panel.updateCurrentObjects(itemName);
                }
            }
        });
        this.deleteItem.addActionListener(e -> {
            String itemName = this.nameField.getText();
            parent.remove(itemName);
            panel.updateCurrentObjects(null);
        });
    }
    public List<String> getScripts(String type, PluginBuilderPanel panel) {
        Module<? extends ContentObject> scriptsModule = panel.getModule("Scripts");
        List<String> scripts = new ArrayList<>();
        for (String key : scriptsModule.getItemKeys()) {
            String scriptType = ((ContentObjectScript) scriptsModule.getItem(key)).getScriptType();
            if(type.equals(scriptType)) scripts.add(key);
        }
        scripts.add("");
        return scripts;
    }
    public void onOpened(Module<T> parent, PluginBuilderPanel panel) {

    }
    public void setName(String name) {
        this.nameField.setText(name);
    }
    public abstract T createItem(String name, String owner);
    public abstract void populate(T item);
    public abstract T getDefault();
    public abstract T consume(String name, JsonObject json, String owner);
}
