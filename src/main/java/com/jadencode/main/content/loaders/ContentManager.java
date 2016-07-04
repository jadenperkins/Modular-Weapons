package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public abstract class ContentManager {
    private final String name;
    private final int loadOrder;

    public ContentManager(String name, int loadOrder) {
        this.name = name;
        this.loadOrder = loadOrder;
    }

    public String getName() {
        return name;
    }

    public int getLoadOrder() {
        return loadOrder;
    }

    public abstract void consume(String name, JsonObject obj);
}