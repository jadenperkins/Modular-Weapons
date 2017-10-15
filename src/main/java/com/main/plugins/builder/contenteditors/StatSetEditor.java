package com.main.plugins.builder.contenteditors;

import com.main.plugins.builder.GuiHelper;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.modules.Module;
import com.main.plugins.pipeline.PipelineObjectStatSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatSetEditor extends ContentEditor<PipelineObjectStatSet> {

    private final JTable statsTable;

    public StatSetEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.above(this);
        this.statsTable = helper.add(new JTable(), "Stats", H_E, V_S, H_L, H_NTR);
    }
    @Override
    public void onOpened(Module<PipelineObjectStatSet> parent, PluginBuilderPanel panel) {
        Module statsModule = panel.getModule("Stats");
        List<String> stats = new ArrayList<>(statsModule.getItemKeys());
        stats.add("");
        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(stats.toArray(new String[0])));

        this.statsTable.setSize(H_L, H_NTR * Math.max(1, statsModule.getItemKeys().size()));
        this.statsTable.setModel(new DefaultTableModel(statsModule.getItemKeys().size(), 2));
        this.statsTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }
    @Override
    public void populate(PipelineObjectStatSet item) {
        for(int row = 0; row < this.statsTable.getModel().getRowCount(); row++) {
            this.statsTable.setValueAt("", row, 0);
            this.statsTable.setValueAt("", row, 1);
        }

        Map<String, Double> stats = item.getStats();
        List<String> keys = new ArrayList<>(stats.keySet());
        for(int row = 0; row < keys.size(); row++) {
            String name = keys.get(row);
            double value = stats.get(name);
            this.statsTable.getModel().setValueAt(name, row, 0);
            this.statsTable.getModel().setValueAt(value, row, 1);
        }
    }
    @Override
    public PipelineObjectStatSet createItem(String name) {
        int rows = this.statsTable.getModel().getRowCount();
        HashMap<String, Double> stats = new HashMap<>();
        for(int row = 0; row < rows; row++) {
            String stat = (String)this.statsTable.getValueAt(row, 0);
            if(stat != null && !stat.isEmpty()) {
                double value = 0;
                try {
                    value = (Double) this.statsTable.getValueAt(row, 1);
                } catch (Exception e) {
                    try {
                        value = Double.parseDouble((String)this.statsTable.getValueAt(row,  1));
                    } catch (Exception e1) {
                    }
                }
                stats.put(stat, value);
            }
        }
        return new PipelineObjectStatSet(name, stats);
    }
    @Override
    public PipelineObjectStatSet getDefault() {
        return new PipelineObjectStatSet("", new HashMap<>());
    }
}