package com.main.constants;

import com.main.material.MaterialType;
import com.main.scripts.ScriptMaterialType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class MaterialTypes {

    private static final HashMap<String, MaterialType> MATERIAL_TYPES = new HashMap<>();
    private static final HashMap<String, ScriptMaterialType> SCRIPTS = new HashMap<>();

    public static ScriptMaterialType script(String name) {
        return SCRIPTS.getOrDefault(name, SCRIPTS.get(null));
    }
    public static HashMap<String, ScriptMaterialType> getScripts() {
        return SCRIPTS;
    }
    public static void register(MaterialType type) {
        MATERIAL_TYPES.put(type.getName(), type);
    }
    public static MaterialType get(String name) {
        return MATERIAL_TYPES.get(name);
    }
    public static List<MaterialType> getMaterialTypes() {
        return new ArrayList<>(MATERIAL_TYPES.values());
    }
}