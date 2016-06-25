package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;

/**
 * Created by gtrpl on 6/18/2016.
 */
public abstract class ContentObject {
    private final String owner;
    private final String name;
    public ContentObject(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }
    @Override
    public String toString() {
        return this.name;
    }
    public abstract void toJson(JsonObject json);
}