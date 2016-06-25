package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectItemPartMaterialized extends ContentObject {

    private final String nameMod;
    private final String partInfo;
    private final float weight;
    private final String partType;
    private final String statSet;
    private final String iconName;
    private final String script;
    private final List<String> materialTypes;

    public ContentObjectItemPartMaterialized(String name, String owner, String nameMod, String partInfo, float weight, String partType, String statSet, String icon, String s, List<String> mats) {
        super(name, owner);
        this.nameMod = nameMod;
        this.partInfo = partInfo;
        this.weight = weight > 0 ? weight : 1F;
        this.partType = partType;
        this.statSet = statSet;
        this.iconName = icon;
        this.script = s;
        this.materialTypes = mats;
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
    public String getScript() {
        return script;
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
                .add("script", this.script)
                .add("materials", JsonHelper.toArray(this.materialTypes));
    }
}