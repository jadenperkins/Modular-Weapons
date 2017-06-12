package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeModular;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemTypeModularEditor extends ContentEditor<ContentObjectItemTypeModular> {

    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> scriptSelection;
    private final JTextField weightField;
    private final JComboBox<String> primarySelection;
    private final JComboBox<String> anchorSelection;
    private final JTable partsList;
    private final JTable optionalList;

    public ItemTypeModularEditor(PluginBuilderPanel parent) {
        super(Strings.ContentEditors.MODULAR_ITEM_TYPES, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E, H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.primarySelection = helper.add(new JComboBox<>(), "Primary Part", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.anchorSelection = helper.add(new JComboBox<>(), "Anchor Part", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partsList = helper.add(new JTable(), "Part Types", H_E, V_S, H_L, 10 * H_NTR, GuiHelper.Align.ABOVE);
        this.optionalList = helper.add(new JTable(), "Attachments", H_E + H_L + H_PAD, V_S, H_L, 10 * H_NTR, GuiHelper.Align.ABOVE);
    }

    @Override
    public void populate(ContentObjectItemTypeModular item) {
        this.statSetSelection.setSelectedItem(item.getStatSetName());
        this.scriptSelection.setSelectedItem(item.getScriptName());
        this.weightField.setText(item.getWeight() + "");
        this.primarySelection.setSelectedItem(item.getPrimaryPart());
        this.anchorSelection.setSelectedItem(item.getAnchorPart());

        for (int row = 0; row < this.partsList.getModel().getRowCount(); row++) {
            this.partsList.setValueAt("", row, 0);
        }

        List<String> partTypes = item.getRequiredParts();

        for (int i = 0; i < partTypes.size(); i++) {
            String partType = partTypes.get(i);
            this.partsList.getModel().setValueAt(partType, i, 0);
        }

        List<String> optional = item.getOptionalParts();

        for (int i = 0; i < optional.size(); i++) {
            String partType = optional.get(i);
            this.optionalList.getModel().setValueAt(partType, i, 0);
        }
    }

    @Override
    public void onOpened(Module<ContentObjectItemTypeModular> parent, PluginBuilderPanel panel) {
        Module statSetModule = panel.getModule("Stat Sets");
        List<String> statSets = statSetModule.getItemKeys();
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        List<String> scripts = this.getScripts("items", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = new ArrayList<>(partTypesModule.getItemKeys());
        partTypes.add("");


        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));
        this.primarySelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));
        this.anchorSelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));
        this.partsList.setModel(new DefaultTableModel(partTypes.size(), 1));
        this.partsList.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
        this.optionalList.setModel(new DefaultTableModel(partTypes.size(), 1));
        this.optionalList.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }

    @Override
    public ContentObjectItemTypeModular createItem(String name, String owner) {
        String stat = (String) this.statSetSelection.getSelectedItem();
        String script = (String) this.scriptSelection.getSelectedItem();
        float weight = this.getFloat(this.weightField);
        String primary = (String) this.primarySelection.getSelectedItem();
        String anchor = (String) this.anchorSelection.getSelectedItem();

        List<String> parts = new ArrayList<>();
        int rows = this.partsList.getModel().getRowCount();
        for (int row = 0; row < rows; row++) {
            String part = (String) this.partsList.getValueAt(row, 0);
            if (part != null && !part.isEmpty()) {
                parts.add(part);
            }
        }

        List<String> opt = new ArrayList<>();
        rows = this.optionalList.getModel().getRowCount();
        for (int row = 0; row < rows; row++) {
            String part = (String) this.optionalList.getValueAt(row, 0);
            if (part != null && !part.isEmpty()) {
                opt.add(part);
            }
        }

        return new ContentObjectItemTypeModular(name, owner, stat, script, weight, primary, anchor, parts, opt);
    }

    public float getFloat(JTextField field) {
        float value;
        try {
            value = Float.parseFloat(field.getText());
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }

    @Override
    public ContentObjectItemTypeModular getDefault() {
        return new ContentObjectItemTypeModular("", "", "", "", 1F, "", "", new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public ContentObjectItemTypeModular consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String statSet = helper.getString("stats");
        String script = helper.getString("script");
        float weight = helper.getFloat("weight");
        String primary = helper.getString("primary");
        String anchor = helper.getString("anchor");
        List<String> parts = JsonHelper.fromArray(helper.getArray("parts"));
        List<String> opt = JsonHelper.fromArray(helper.getArray("optional parts"));

        return new ContentObjectItemTypeModular(name, owner, statSet, script, weight, primary, anchor, parts, opt);
    }
}