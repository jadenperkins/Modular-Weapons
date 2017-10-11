package com.main.constants;

import com.main.material.MaterialModifier;
import com.main.material.MaterialType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class MaterialModifiers {
    private static final HashMap<MaterialType, List<MaterialModifier>> MODIFIERS = new HashMap<>();

    public static void register(MaterialType type, MaterialModifier modifier) {
        if(!MODIFIERS.containsKey(type)) MODIFIERS.put(type, new ArrayList<>());
        MODIFIERS.get(type).add(modifier);
    }
    public static List<MaterialModifier> getModifiers(MaterialType type) {
        return !MODIFIERS.containsKey(type) ? new ArrayList<>() : MODIFIERS.get(type);
    }
}
