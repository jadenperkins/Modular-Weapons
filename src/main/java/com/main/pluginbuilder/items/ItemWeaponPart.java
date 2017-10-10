package com.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.main.pluginbuilder.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemWeaponPart extends Item {

    private final String nameMod;
    private final String partInfo;
    private final float weight;
    private final String partType;
    private final String statSet;
    private final String iconName;
    private final List<String> materialTypes;

    public ItemWeaponPart(String name, String owner, String nameMod, String partInfo, float weight, String partType, String statSet, String icon, List<String> materialTypes) {
        super(name, owner);
        this.nameMod = nameMod;
        this.partInfo = partInfo;
        this.weight = weight > 0 ? weight : 1F;
        this.partType = partType;
        this.statSet = statSet;
        this.iconName = icon;
        this.materialTypes = materialTypes;
    }
    public String getIconName() {
        return iconName;
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
    public String getStatSet() {
        return statSet;
    }
    public List<String> getMaterialTypes() {
        return materialTypes;
    }
    @Override
    public void toJson(JsonObject json) {
        JsonHelper helper = new JsonHelper(json)
                .add("nameMod", this.nameMod)
                .add("partInfo", this.partInfo)
                .add("weight", this.weight)
                .add("partType", this.partType)
                .add("stats", this.statSet)
                .add("icon", this.iconName)
                .addNotEmpty("materials", JsonHelper.toArray(this.materialTypes));
    }
}