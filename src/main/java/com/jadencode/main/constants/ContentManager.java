package com.jadencode.main.constants;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by gtrpl on 6/15/2016.
 */
public abstract class ContentManager {
    private final String name;
    private final int loadOrder;

    public ContentManager(String name, int loadOrder) {
        this.name = name;
        this.loadOrder = loadOrder;
    }
    public final String getName() {
        return name;
    }
    public final int getLoadOrder() {
        return loadOrder;
    }
    public abstract void consume(String name, JsonObject obj);
    public JsonArray supply() {
        return new JsonArray();
    }
}
