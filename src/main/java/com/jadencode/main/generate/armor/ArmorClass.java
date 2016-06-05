package com.jadencode.main.generate.armor;

import com.jadencode.main.util.Weightable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class ArmorClass implements Weightable {

    private static final List<ArmorClass> ARMOR_CLASSES = new ArrayList<>();

//    public static final ArmorClass ARMOR_CLASS_MELEE_HELMET = new ArmorClass("Melee Helmet", ArmorClass.mapParts(
//        ArmorGenerator.MELEE_HELMET_BASE_KEY, ArmorGenerator.MELEE_HELMET_ADD_KEY),
//        a -> "Melee Helmet", 12F, 15F, 10F, 5F);
//    public static final  ArmorClass       WEAPON_CLASS_SWORD = new ArmorClass("Sword", ArmorClass.mapParts(
//            WeaponGenerator.MELEE_HELMET_BASE, WeaponGenerator.MELEE_CHEST_BASE, WeaponGenerator.SWORD_BLADES_KEY),
//            w -> {
//                WeaponPartBase grip = w.getArmorParts().get(WeaponGenerator.MELEE_HELMET_BASE).getArmorPart().getBaseArmorPart();
//                WeaponPartBase hilt = w.getArmorParts().get(WeaponGenerator.MELEE_CHEST_BASE).getArmorPart().getBaseArmorPart();
//                WeaponPartBase blade = w.getArmorParts().get(WeaponGenerator.SWORD_BLADES_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", grip.getNameMod(), hilt.getNameMod(), blade.getNameMod());
//            }, 75F, 3F, 4F);

    private final HashMap<String, List<ArmorPart>> armorParts;
    private final String                           armorClassName;
    private final int                              enchantmentMin;
    private final int                              enchantmentMax;
    private final float                            enchantmentChance;
    private final float                            baseSlash;
    private final float                            basePierce;
    private final float                            baseBlunt;
    private final float                            baseMobility;

    public ArmorClass(String name, HashMap<String, List<ArmorPart>> map, float sls, float prc, float bnt, float mob) {
        this(name, 0, 2, 0.15F, map, sls, prc, bnt, mob);
    }

    public ArmorClass(String name, int min, int max, float chance, HashMap<String, List<ArmorPart>> map, float sls, float prc, float bnt, float mob) {
        this.armorClassName = name;
        this.armorParts = map;
        this.enchantmentMin = min;
        this.enchantmentMax = max;
        this.enchantmentChance = chance;
        this.baseSlash = sls;
        this.basePierce = prc;
        this.baseBlunt = bnt;
        this.baseMobility = mob;
        ARMOR_CLASSES.add(this);
    }

    public static HashMap<String, List<ArmorPart>> mapParts(String... keys) {
        HashMap<String, List<ArmorPart>> map = new HashMap<>();
        for (String key : keys) {
            map.put(key, ArmorGenerator.getArmorParts(key));
        }
        return map;
    }
    public float getEnchantmentChance() {
        return enchantmentChance;
    }

    public String getArmorClassName() {
        return armorClassName;
    }

    public int getEnchantmentMax() {
        return enchantmentMax;
    }

    public int getEnchantmentMin() {
        return enchantmentMin;
    }

    public static List<ArmorClass> getArmorClasses() {
        return ARMOR_CLASSES;
    }

    @Override
    public float getWeight() {
        return 1F;
    }

    public HashMap<String, List<ArmorPart>> getArmorPartsLists() {
        return this.armorParts;
    }

    public float getBaseSlash() {
        return baseSlash;
    }

    public float getBasePierce() {
        return basePierce;
    }

    public float getBaseBlunt() {
        return baseBlunt;
    }

    public float getBaseMobility() {
        return baseMobility;
    }

    //    public abstract Generator<? extends WeaponInstance> getGenerator();
}