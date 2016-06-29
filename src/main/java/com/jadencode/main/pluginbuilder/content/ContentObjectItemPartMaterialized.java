package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.awt.*;
import java.util.HashMap;
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
    private final HashMap<String, Point.Double> joints;

    public ContentObjectItemPartMaterialized(String name, String owner, String nameMod, String partInfo, float weight, String partType, String statSet, String icon, String s, List<String> mats, HashMap<String, Point.Double> joints) {
        super(name, owner);
        this.nameMod = nameMod;
        this.partInfo = partInfo;
        this.weight = weight > 0 ? weight : 1F;
        this.partType = partType;
        this.statSet = statSet;
        this.iconName = icon;
        this.script = s;
        this.materialTypes = mats;
        this.joints = joints;
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
    public HashMap<String, Point.Double> getJoints() {
        return joints;
    }

    @Override
    public void toJson(JsonObject json) {
        JsonArray array = new JsonArray();
        for (String s : this.joints.keySet()) {
            array.add(new JsonHelper(new JsonObject()).add("name", s).add("x", joints.get(s).x).add("y", joints.get(s).y).get());
        }

        JsonHelper helper = new JsonHelper(json)
                .add("nameMod", this.nameMod)
                .add("partInfo", this.partInfo)
                .add("weight", this.weight)
                .add("partType", this.partType)
                .add("stats", this.statSet)
                .add("icon", this.iconName)
                .add("script", this.script)
                .add("materials", JsonHelper.toArray(this.materialTypes))
                .add("joints", array);
    }
}