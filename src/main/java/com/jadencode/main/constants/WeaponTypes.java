package com.jadencode.main.constants;

import com.jadencode.main.generate.weapon.WeaponType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class WeaponTypes {
    private static final HashMap<String, WeaponType> WEAPON_TYPES = new HashMap<>();

    public static List<WeaponType> getWeaponTypes() {
        return new ArrayList<>(WEAPON_TYPES.values());
    }
    public static void register(WeaponType type) {
        WEAPON_TYPES.put(type.getWeaponTypeName(), type);
    }
}