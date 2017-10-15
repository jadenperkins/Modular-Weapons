package com.main.plugins.builder.contenteditors;

import com.main.plugins.builder.GuiHelper;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.modules.Module;
import com.main.plugins.pipeline.PipelineObjectWeaponType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class WeaponTypeEditor extends ContentEditor<PipelineObjectWeaponType> {

    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> scriptSelection;
    private final JTextField weightField;
    private final JComboBox<String> primarySelection;
    private final JTable partsList;

    public WeaponTypeEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E, H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.primarySelection = helper.add(new JComboBox<>(), "Primary Part", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partsList = helper.add(new JTable(), "Part Types", H_E, V_S, H_L, 10 * H_NTR, GuiHelper.Align.ABOVE);
    }
    @Override
    public void populate(PipelineObjectWeaponType item) {
        this.statSetSelection.setSelectedItem(item.getStats());
        this.scriptSelection.setSelectedItem(item.getScript());
        this.weightField.setText(item.getWeight() + "");
        this.primarySelection.setSelectedItem(item.getPrimary());

        for(int row = 0; row < this.partsList.getModel().getRowCount(); row++) {
            this.partsList.setValueAt("", row, 0);
        }

        List<String> partTypes = item.getParts();

        for (int i = 0; i < partTypes.size(); i++) {
            String partType = partTypes.get(i);
            this.partsList.getModel().setValueAt(partType, i, 0);
        }
    }
    @Override
    public void onOpened(Module<PipelineObjectWeaponType> parent, PluginBuilderPanel panel) {
        Module statSetModule = panel.getModule("Stat Sets");
        List<String> statSets = statSetModule.getItemKeys();
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        List<String> scripts = this.getScripts("weapons", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = new ArrayList<>(partTypesModule.getItemKeys());

        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        partTypes.add("");
        this.primarySelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        this.partsList.setModel(new DefaultTableModel(partTypes.size(), 1));
        this.partsList.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }
    @Override
    public PipelineObjectWeaponType createItem(String name) {
        String stat = (String)this.statSetSelection.getSelectedItem();
        String script = (String)this.scriptSelection.getSelectedItem();
        float weight = this.getFloat(this.weightField);
        String primary = (String)this.primarySelection.getSelectedItem();

        List<String> parts = new ArrayList<>();
        int rows = this.partsList.getModel().getRowCount();
        for(int row = 0; row < rows; row++) {
            String part = (String) this.partsList.getValueAt(row, 0);
            if(part != null && !part.isEmpty()) {
                parts.add(part);
            }
        }

        return new PipelineObjectWeaponType(name, script, stat, weight, parts, primary);
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
    public PipelineObjectWeaponType getDefault() {
        return new PipelineObjectWeaponType("", "", "", 1F, new ArrayList<>(), "");
    }
}