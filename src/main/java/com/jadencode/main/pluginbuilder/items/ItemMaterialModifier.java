package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.jadencode.main.pluginbuilder.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemMaterialModifier extends Item {

//    "name": "Lunar", "color": "MOD_LUNAR", "weight": 1.0, "level": 0.5, "mod": 1.2, "materials": ["Metal"]},
    private final String colorName;
    private final float weight;
    private final float level;
    private final float mod;
    private final List<String> materialTypes;

    public ItemMaterialModifier(String name, String color, float w, float l, float m, List<String> types) {
        super(name);
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