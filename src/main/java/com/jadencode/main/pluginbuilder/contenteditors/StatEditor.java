package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectStat;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;

import javax.swing.*;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatEditor extends ContentEditor<ContentObjectStat> {

    private final JComboBox<String> scriptSelection;
    private final JTextField defaultValue;

    public StatEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E, H_L, H_FLD);
        this.defaultValue = helper.add(new JTextField(), "Default Value", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
    }

    @Override
    public void onOpened(Module<ContentObjectStat> parent, PluginBuilderPanel panel) {
        List<String> scripts = this.getScripts("stats", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }

    @Override
    public void populate(ContentObjectStat item) {
        this.scriptSelection.setSelectedItem(item.getScriptName());
        this.defaultValue.setText(item.getDefaultValue() + "");
    }

    @Override
    public ContentObjectStat createItem(String name, String owner) {
        double value;
        try {
            value = Double.parseDouble(this.defaultValue.getText());
        } catch (Exception e) {
            value = 0;
            this.defaultValue.setText("0.0");
        }
        return new ContentObjectStat(name, owner, (String) this.scriptSelection.getSelectedItem(), value);
    }

    @Override
    public ContentObjectStat getDefault() {
        return new ContentObjectStat("", "", "", 0.0);
    }

    @Override
    public ContentObjectStat consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        return new ContentObjectStat(name, owner, helper.getString("script"), helper.getDouble("default"));
    }
}