package com.main.plugins.modules;

import com.main.constants.StatSets;
import com.main.constants.Stats;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderTable;
import com.main.plugins.pipeline.PipelineObjectStatSet;
import com.main.stat.StatSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatSetCreator extends Module<PipelineObjectStatSet> {

    public static final String STATS_TABLE = "statsTable";

    public ModuleStatSetCreator() {
        super("Stat Sets", 4, Plugin::getStatSets);
    }
    @Override
    public PipelineObjectStatSet createItem(ContentEditor<PipelineObjectStatSet> contentEditor, String name) {
        BuilderTable statsTable = contentEditor.getComponent(STATS_TABLE).getAsTable();
        int rows = statsTable.getModel().getRowCount();
        Map<String, Double> stats = new HashMap<>();
        for (int row = 0; row < rows; row++) {
            String stat = (String) statsTable.getModel().getValueAt(row, 0);
            if (stat == null || stat.isEmpty()) continue;

            double value = 0;
            try {
                value = (Double) statsTable.getModel().getValueAt(row, 1);
            } catch (Exception e) {
                try {
                    value = Double.parseDouble((String) statsTable.getModel().getValueAt(row, 1));
                } catch (Exception e1) {

                }
            }
            stats.put(stat, value);
        }
        return new PipelineObjectStatSet(name, stats);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectStatSet> contentEditor) {
        contentEditor.addComponent(STATS_TABLE, new BuilderTable(), H_E, V_S, H_L, H_NTR);
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectStatSet> contentEditor, PipelineObjectStatSet pipelineObject) {
        BuilderTable statsTable = contentEditor.getComponent(STATS_TABLE).getAsTable();
        int rows = statsTable.getModel().getRowCount();
        for (int row = 0; row < rows; row++) {
            statsTable.getModel().setValueAt("", row, 0);
            statsTable.getModel().setValueAt("", row, 1);
        }

        Map<String, Double> stats = pipelineObject.getStats();
        List<String> keys = new ArrayList<>(stats.keySet());
        int size = keys.size();
        for (int row = 0; row < size; row++) {
            String name = keys.get(row);
            double value = stats.get(name);
            statsTable.getModel().setValueAt(name, row, 0);
            statsTable.getModel().setValueAt(value, row, 1);
        }
    }
    @Override
    public void onOpened(ContentEditor<PipelineObjectStatSet> contentEditor, PluginBuilderPanel panel) {
        Module statsModule = panel.getModule("Stats");
        List<String> stats = new ArrayList<>(statsModule.getItemKeys());
        stats.add("");
        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(stats.toArray(new String[0])));

        BuilderTable statsTable = contentEditor.getComponent(STATS_TABLE).getAsTable();

        statsTable.setSize(H_L, H_NTR * Math.max(1, statsModule.getItemKeys().size()));
        statsTable.setModel(new DefaultTableModel(statsModule.getItemKeys().size(), 2));
        statsTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }
    @Override
    public PipelineObjectStatSet getDefault() {
        return new PipelineObjectStatSet("", new HashMap<>());
    }

    @Override
    public void consume(PipelineObjectStatSet object) {
        StatSet set = new StatSet();
        Map<String, Double> stats = object.getStats();
        Set<String> statNames = stats.keySet();
        for (String stat : statNames) {
            set.add(Stats.get(stat), stats.get(stat));
        }
        StatSets.register(object.getName(), set);
    }
}