package com.jadencode.main.constants;

import com.jadencode.main.generate.weapon.WeaponType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class WeaponTypes {
    private static final List<WeaponType> WEAPON_TYPES = new ArrayList<>();

    public static final WeaponType WEAPON_CLASS_SWORD = new WeaponType("Sword", StatSets.BASE_SWORD,
            Arrays.asList(WeaponParts.PART_SWORD_HILT, WeaponParts.PART_SWORD_GRIP, WeaponParts.PART_SWORD_BLADE));

    public static List<WeaponType> getWeaponTypes() {
        return WEAPON_TYPES;
    }
    public static void addWeaponType(WeaponType type) {
        WEAPON_TYPES.add(type);
    }
}