package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectMaterialModifier extends ContentObject {

    private final String colorName;
    private final float weight;
    private final float level;
    private final float mod;
    private final List<String> materialTypes;

    public ContentObjectMaterialModifier(String name, String owner, String color, float w, float l, float m, List<String> types) {
        super(name, owner);
        this.colorName = color;
        this.weight = w > 0 ? w : 1;
        this.level = l;
        this.mod = m;
        this.materialTypes = types;
    }
    public String getColorName() {
        return colorName;
    }
    public float getWeight() {
        return weight;
    }
    public float getLevel() {
        return level;
    }
    public float getMod() {
        return mod;
    }
    public List<String> getMaterialTypes() {
        return materialTypes;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("color", this.colorName)
                .add("weight", this.weight)
                .add("level", this.level)
                .add("mod", this.mod)
                .add("materials", JsonHelper.toArray(this.materialTypes));
    }
}