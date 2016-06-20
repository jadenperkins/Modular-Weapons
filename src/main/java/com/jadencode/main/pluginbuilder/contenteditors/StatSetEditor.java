package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.items.ItemStat;
import com.jadencode.main.pluginbuilder.items.ItemStatSet;
import com.jadencode.main.pluginbuilder.modules.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class StatSetEditor extends ContentEditor<ItemStatSet> {

    private final JTable statsTable;

    public StatSetEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);

        this.statsTable = this.create(new JTable(new DefaultTableModel(10, 2)), 10, 140, 200, 160);

    }
    @Override
    public void populate(ItemStatSet item) {
        this.statsTable.setModel(new DefaultTableModel(10, 2));
        HashMap<String, Double> stats = item.getStats();
        List<String> keys = new ArrayList<>(stats.keySet());
        for(int row = 0; row < keys.size(); row++) {
            String name = keys.get(row);
            double value = stats.get(name);
            this.statsTable.getModel().setValueAt(name, row, 0);
            this.statsTable.getModel().setValueAt(value, row, 1);
        }
    }
    @Override
    public ItemStatSet createItem(String name) {
        int rows = this.statsTable.getModel().getRowCount();
        HashMap<String, Double> stats = new HashMap<>();
        for(int row = 0; row < rows; row++) {
            String stat = (String)this.statsTable.getValueAt(row, 0);
            if(stat != null && !stat.isEmpty()) {
                double value;
                try {
                    value = Double.parseDouble((String)this.statsTable.getValueAt(row, 1));
                } catch (Exception e) {
                    value = 0;
                }
                stats.put(stat, value);
            }
        }
        return new ItemStatSet(name, stats);
    }
    @Override
    public ItemStatSet getDefault() {
        return new ItemStatSet("", new HashMap<>());
    }

    @Override
    public ItemStatSet consume(String name, JsonObject json) {
        JsonHelper helper = new JsonHelper(json);

        HashMap<String, Double> stats = new HashMap<>();
        JsonArray array = helper.getArray("stats");
        for (JsonElement jsonElement : array) {
            JsonHelper obj = new JsonHelper(jsonElement.getAsJsonObject());
            stats.put(obj.getString("stat"), obj.getDouble("value"));
        }
        return new ItemStatSet(name, stats);
    }
}