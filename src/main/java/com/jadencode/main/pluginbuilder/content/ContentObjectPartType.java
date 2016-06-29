package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectPartType extends ContentObject {

    private final String iconName;
    private final List<String> joints;

    public ContentObjectPartType(String name, String owner, String icon, List<String> joints) {
        super(name, owner);
        this.iconName = icon;
        this.joints = joints;
    }
    public String getIconName() {
        return this.iconName;
    }

    public List<String> getJoints() {
        return joints;
    }

    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("icon", this.iconName)
                .add("joints", JsonHelper.toArray(this.joints));
    }
}