package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.util.HashMap;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectStatSet extends ContentObject {

    private final HashMap<String, Double> stats;

    public ContentObjectStatSet(String name, String owner, HashMap<String, Double> stats) {
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