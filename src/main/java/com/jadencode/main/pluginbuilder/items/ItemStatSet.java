package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemStatSet extends Item {

    private final HashMap<String, Double> stats;

    public ItemStatSet(String name, HashMap<String, Double> stats) {
        super(name);
        this.stats = stats;
    }
    public HashMap<String, Double> getStats() {
        return this.stats;
    }
    @Override
    public void toJson(JsonObject json) {
        JsonArray array = new JsonArray();
        for (String statName : this.stats.keySet()) {
            double value = this.stats.get(statName);
            JsonObject obj = new JsonObject();
            obj.add("stat", new JsonPrimitive(statName));
            obj.add("value", new JsonPrimitive(value));
            array.add(obj);
        }
        json.add("stats", array);
    }
}