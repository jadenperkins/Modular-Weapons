package com.jadencode.main.constants;

import com.jadencode.main.generate.weapon.WeaponPartType;

import java.util.HashMap;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class PartTypes {

    private static final HashMap<String, WeaponPartType> PART_TYPES = new HashMap<>();

    public static final WeaponPartType get(String name) {
        return PART_TYPES.get(name);
    }
    public static void register(WeaponPartType type) {
        PART_TYPES.put(type.getTypeName(), type);
    }
}
