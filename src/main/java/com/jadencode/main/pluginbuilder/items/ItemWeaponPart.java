package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemWeaponPart extends Item {

    private final String nameMod;
    private final String partInfo;
    private final float weight;
    private final String partType;
    private final List<String> materialTypes;

    public ItemWeaponPart(String name, String nameMod, String partInfo, float weight, String partType, List<String> materialTypes) {
        super(name);
        this.nameMod = nameMod;
        this.partInfo = partInfo;
        this.weight = weight;
        this.partType = partType;
        this.materialTypes = materialTypes;
    }
    public String getNameMod() {
        return nameMod;
    }
    public String getPartInfo() {
        return partInfo;
    }
    public float getWeight() {
        return weight;
    }
    public String getPartType() {
        return partType;
    }
    public List<String> getMaterialTypes() {
        return materialTypes;
    }
    @Override
    public void toJson(JsonObject json) {
        json.add("nameMod", new JsonPrimitive(this.nameMod));

        if(this.partInfo != null && !this.partInfo.isEmpty())
            json.add("partInfo", new JsonPrimitive(this.partInfo));

        if(this.weight > 0F)
            json.add("weight", new JsonPrimitive(this.weight));

        json.add("partType", new JsonPrimitive(this.partType));


        if(!this.materialTypes.isEmpty()) {
            JsonArray array = new JsonArray();
            for (String partType : this.materialTypes)
                array.add(new JsonPrimitive(partType));
            json.add("materials", array);
        }
    }
}