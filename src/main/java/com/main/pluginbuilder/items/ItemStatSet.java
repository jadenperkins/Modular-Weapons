package com.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.main.pluginbuilder.JsonHelper;

import java.util.HashMap;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemStatSet extends Item {

    private final HashMap<String, Double> stats;

    public ItemStatSet(String name, String owner, HashMap<String, Double> stats) {
        super(name, owner);
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