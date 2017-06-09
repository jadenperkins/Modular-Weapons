package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectStatSet;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatSetEditor extends ContentEditor<ContentObjectStatSet> {

    private final JTable statsTable;

    public StatSetEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.above(this);
        this.statsTable = helper.add(new JTable(), "Stats", H_E, V_S, H_L, H_NTR);
    }

    @Override
    public void onOpened(Module<ContentObjectStatSet> parent, PluginBuilderPanel panel) {
        Module statsModule = panel.getModule("Stats");
        List<String> stats = new ArrayList<>(statsModule.getItemKeys());
        stats.add("");
        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(stats.toArray(new String[0])));

        this.statsTable.setSize(H_L, H_NTR * Math.max(1, statsModule.getItemKeys().size()));
        this.statsTable.setModel(new DefaultTableModel(statsModule.getItemKeys().size(), 2));
        this.statsTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }

    @Override
    public void populate(ContentObjectStatSet item) {
        for (int row = 0; row < this.statsTable.getModel().getRowCount(); row++) {
            this.statsTable.setValueAt("", row, 0);
            this.statsTable.setValueAt("", row, 1);
        }

        HashMap<String, Double> stats = item.getStats();
        List<String> keys = new ArrayList<>(stats.keySet());
        for (int row = 0; row < keys.size(); row++) {
            String name = keys.get(row);
            double value = stats.get(name);
            this.statsTable.getModel().setValueAt(name, row, 0);
            this.statsTable.getModel().setValueAt(value, row, 1);
        }
    }

    @Override
    public ContentObjectStatSet createItem(String name, String owner) {
        int rows = this.statsTable.getModel().getRowCount();
        HashMap<String, Double> stats = new HashMap<>();
        for (int row = 0; row < rows; row++) {
            String stat = (String) this.statsTable.getValueAt(row, 0);
            if (stat != null && !stat.isEmpty()) {
                double value = 0;
                try {
                    value = (Double) this.statsTable.getValueAt(row, 1);
                } catch (Exception e) {
                    try {
                        value = Double.parseDouble((String) this.statsTable.getValueAt(row, 1));
                    } catch (Exception ignored) {
                    }
                }
                stats.put(stat, value);
            }
        }
        return new ContentObjectStatSet(name, owner, stats);
    }

    @Override
    public ContentObjectStatSet getDefault() {
        return new ContentObjectStatSet("", "", new HashMap<>());
    }

    @Override
    public ContentObjectStatSet consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);

        HashMap<String, Double> stats = new HashMap<>();
        JsonArray array = helper.getArray("stats");
        for (JsonElement jsonElement : array) {
            JsonHelper obj = new JsonHelper(jsonElement.getAsJsonObject());
            stats.put(obj.getString("stat"), obj.getDouble("value"));
        }
        return new ContentObjectStatSet(name, owner, stats);
    }
}