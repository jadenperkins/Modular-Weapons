package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;

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
        new JsonHelper(json).add("stats", JsonHelper.toArray(this.stats, "stat", "value"));
    }
}