package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.PartTypes;
import com.jadencode.main.generate.weapon.WeaponPartType;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class PartTypeLoader extends ContentManager {
    public PartTypeLoader() {
        super("Part Types", 1);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        PartTypes.register(new WeaponPartType(name));
    }
}