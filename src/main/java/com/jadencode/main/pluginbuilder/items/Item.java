package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;

/**
 * Created by gtrpl on 6/18/2016.
 */
public abstract class Item {
    private final String name;
    public Item(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return this.name;
    }
    public abstract void toJson(JsonObject json);
}