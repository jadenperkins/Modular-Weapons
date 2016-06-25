package com.jadencode.main.constants;

import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.generate.item.WeaponType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class ItemTypes {
    private static final HashMap<String, WeaponType> WEAPON_TYPES = new HashMap<>();
    private static final HashMap<String, ScriptItem> SCRIPTS = new HashMap<>();

    public static HashMap<String, ScriptItem> getScripts() {
        return SCRIPTS;
    }
    public static ScriptItem script(String name) {
        return SCRIPTS.getOrDefault(name, SCRIPTS.get(null));
    }

    public static List<WeaponType> getWeaponTypes() {
        return new ArrayList<>(WEAPON_TYPES.values());
    }
    public static void register(WeaponType type) {
        WEAPON_TYPES.put(type.getWeaponTypeName(), type);
    }
    public static WeaponType get(String name) {
        return WEAPON_TYPES.get(name);
    }
}