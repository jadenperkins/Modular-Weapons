package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.scripts.ScriptMaterialType;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialTypeLoader extends ContentManager {
    public MaterialTypeLoader() {
        super("Material Types", 0);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        String scriptName = obj.has("script") ? obj.get("script").getAsString() : null;
        ScriptMaterialType script = MaterialTypes.script(scriptName);
        MaterialTypes.register(new MaterialType(name, script));
    }
}