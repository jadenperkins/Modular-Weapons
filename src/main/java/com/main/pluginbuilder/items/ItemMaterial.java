package com.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.main.pluginbuilder.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemMaterial extends Item {

    private final String colorName;
    private final float weight;
    private final float mod;
    private final int level;
    private final String materialType;

    public ItemMaterial(String name, String owner, String color, float w, float m, int l, String type) {
        super(name, owner);
        this.colorName = color;
        this.weight = w > 0 ? w : 1;
        this.mod = m;
        this.level = l;
        this.materialType = type;
    }
    public String getColorName() {
        return colorName;
    }
    public float getWeight() {
        return weight;
    }
    public float getMod() {
        return mod;
    }
    public int getLevel() {
        return level;
    }

    public String getMaterialType() {
        return materialType;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("color", this.colorName)
                .add("weight", this.weight)
                .add("mod", this.mod)
                .add("level", this.level)
                .add("material", this.materialType);
    }
}