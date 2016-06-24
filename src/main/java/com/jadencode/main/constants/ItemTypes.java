package com.jadencode.main.constants;

import com.jadencode.main.scripts.ScriptWeapon;
import com.jadencode.main.generate.item.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class ItemTypes {
    private static final HashMap<String, ItemType> WEAPON_TYPES = new HashMap<>();
    private static final HashMap<String, ScriptWeapon> SCRIPTS = new HashMap<>();

    public static HashMap<String, ScriptWeapon> getScripts() {
        return SCRIPTS;
    }
    public static ScriptWeapon script(String name) {
        return SCRIPTS.getOrDefault(name, SCRIPTS.get(null));
    }

    public static List<ItemType> getWeaponTypes() {
        return new ArrayList<>(WEAPON_TYPES.values());
    }
    public static void register(ItemType type) {
        WEAPON_TYPES.put(type.getWeaponTypeName(), type);
    }
    public static ItemType get(String name) {
        return WEAPON_TYPES.get(name);
    }
}