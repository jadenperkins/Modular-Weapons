package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.util.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectPartType;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PartTypeEditor extends ContentEditor<ContentObjectPartType> {

    private final JComboBox<String> iconSelection;
    private final JTable jointsTable;

    public PartTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E, H_L, H_FLD);
        this.jointsTable = helper.add(new JTable(new DefaultTableModel(10, 1)), "Joints", H_E, V_S, H_L, 10 * H_NTR, GuiHelper.Align.ABOVE);
    }
    @Override
    public void onOpened(Module<ContentObjectPartType> parent, PluginBuilderPanel panel) {
        Module iconModules = panel.getModule("Icons");
        List<String> icons = iconModules.getItemKeys();
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));

        this.jointsTable.setModel(new DefaultTableModel(10, 1));
    }
    @Override
    public void populate(ContentObjectPartType item) {
        this.iconSelection.setSelectedItem(item.getIconName());

        for(int row = 0; row < 10; row++) {
            this.jointsTable.setValueAt("", row, 0);
        }

        List<String> keys = new ArrayList<>(item.getJoints());
        for(int row = 0; row < keys.size(); row++) {
            String name = keys.get(row);
            this.jointsTable.getModel().setValueAt(name, row, 0);
        }
    }
    @Override
    public ContentObjectPartType createItem(String name, String owner) {
        int rows = this.jointsTable.getModel().getRowCount();
        List<String> joints = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            String joint = (String)this.jointsTable.getValueAt(row, 0);
            if(joint != null && !joint.isEmpty()) {
                joints.add(joint);
            }
        }
        return new ContentObjectPartType(name, owner, (String)this.iconSelection.getSelectedItem(), joints);
    }
    @Override
    public ContentObjectPartType getDefault() {
        return new ContentObjectPartType("", "", "", new ArrayList<>());
    }

    @Override
    public ContentObjectPartType consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        return new ContentObjectPartType(name, owner, helper.getString("icon"), JsonHelper.fromArray(helper.getArray("joints")));
    }
}