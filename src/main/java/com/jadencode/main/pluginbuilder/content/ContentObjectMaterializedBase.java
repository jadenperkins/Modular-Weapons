package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectMaterializedBase extends ContentObject {

    private final String iconName;

    public ContentObjectMaterializedBase(String name, String owner, String icon) {
        super(name, owner);
        this.iconName = icon;
    }
    public String getIconName() {
        return this.iconName;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json).add("icon", this.iconName);
    }
}