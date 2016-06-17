package com.jadencode.main.constants;

import com.jadencode.main.generate.weapon.WeaponType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class WeaponTypes {
    private static final HashMap<String, WeaponType> WEAPON_TYPES = new HashMap<>();

//    public static final WeaponType WEAPON_CLASS_SWORD = new WeaponType("Sword", StatSets.BASE_SWORD,
//            Arrays.asList(PartTypes.PART_SWORD_HILT, PartTypes.PART_SWORD_GRIP, PartTypes.PART_SWORD_BLADE),
//            w -> w.getPart(PartTypes.PART_SWORD_HILT).getNameMod() + " " + w.getPart(PartTypes.PART_SWORD_GRIP).getNameMod()+ " " + w.getPart(PartTypes.PART_SWORD_BLADE).getNameMod());
//
//    public static final WeaponType WEAPON_TYPE_HAMMER = new WeaponType("Hammer", StatSets.BASE_HAMMER,
//            Arrays.asList(PartTypes.PART_HAMMER_HANDLE, PartTypes.PART_HAMMER_HEAD),
//            w -> w.getPart(PartTypes.PART_HAMMER_HEAD).getNameMod() + " " + w.getPart(PartTypes.PART_HAMMER_HANDLE).getNameMod());
//
//    public static final WeaponType WEAPON_TYPE_AXE = new WeaponType("Axe", StatSets.BASE_AXE,
//            Arrays.asList(PartTypes.PART_AXE_HANDLE, PartTypes.PART_AXE_HEAD),
//            w -> w.getPart(PartTypes.PART_AXE_HEAD).getNameMod() + " " + w.getPart(PartTypes.PART_AXE_HANDLE).getNameMod());
//
//    public static final WeaponType WEAPON_TYPE_BOW = new WeaponType("Bow", StatSets.BASE_BOW,
//            Arrays.asList(PartTypes.PART_BOW_GRIP, PartTypes.PART_BOW_LIMBS, PartTypes.PART_BOW_STRING),
//            w -> w.getPart(PartTypes.PART_BOW_GRIP).getNameMod() + " " + w.getPart(PartTypes.PART_BOW_STRING).getNameMod() + " " + w.getPart(PartTypes.PART_BOW_LIMBS).getNameMod());
//
//    public static final WeaponType WEAPON_TYPE_CROSSBOW = new WeaponType("Crossbow", StatSets.BASE_CROSSBOW,
//            Arrays.asList(PartTypes.PART_CBOW_STOCK, PartTypes.PART_CBOW_STRING, PartTypes.PART_CBOW_LIMBS),
//            w -> w.getPart(PartTypes.PART_CBOW_STOCK).getNameMod() + " " + w.getPart(PartTypes.PART_CBOW_STRING).getNameMod() + " " + w.getPart(PartTypes.PART_CBOW_LIMBS).getNameMod());

    public static List<WeaponType> getWeaponTypes() {
        return new ArrayList<>(WEAPON_TYPES.values());
    }
    public static void register(WeaponType type) {
        WEAPON_TYPES.put(type.getWeaponTypeName(), type);
    }
}