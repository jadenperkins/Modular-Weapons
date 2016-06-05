package com.jadencode.main.generate.weapon;

import com.jadencode.main.item.StatSet;
import com.jadencode.main.util.Weightable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public abstract class WeaponClass implements Weightable {

    private static final List<WeaponClass> WEAPON_CLASSES     = new ArrayList<>();
    public static final  WeaponClass       WEAPON_CLASS_SWORD = new WeaponClassSword();

//    public static final  WeaponClass       WEAPON_CLASS_HAMMER   = new WeaponClass("Hammer", WeaponClass.mapParts(
//            WeaponGenerator.HAMMER_HANDLES_KEY, WeaponGenerator.HAMMER_HEADS_KEY),
//            w -> {
//                WeaponPartBase handle = w.getArmorParts().get(WeaponGenerator.HAMMER_HANDLES_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.HAMMER_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s", head.getNameMod(), handle.getNameMod());
//            });
//    public static final  WeaponClass       WEAPON_CLASS_AXE      = new WeaponClass("Axe", WeaponClass.mapParts(
//            WeaponGenerator.AXE_HANDLES_KEY, WeaponGenerator.AXE_HEADS_KEY),
//            w -> {
//                WeaponPartBase handle = w.getArmorParts().get(WeaponGenerator.AXE_HANDLES_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.AXE_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s", head.getNameMod(), handle.getNameMod());
//            });
//    public static final  WeaponClass       WEAPON_CLASS_BOW      = new WeaponClass("Bow", WeaponClass.mapParts(
//            WeaponGenerator.BOW_GRIPS_KEY, WeaponGenerator.BOW_LIMBS_KEY, WeaponGenerator.BOW_STRINGS_KEY),
//            w -> {
//                WeaponPartBase grip = w.getArmorParts().get(WeaponGenerator.BOW_GRIPS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase limbs = w.getArmorParts().get(WeaponGenerator.BOW_LIMBS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase string = w.getArmorParts().get(WeaponGenerator.BOW_STRINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", string.getNameMod(), grip.getNameMod(), limbs.getNameMod());
//            });
//    public static final  WeaponClass       WEAPON_CLASS_ARROW    = new WeaponClass("Arrow", WeaponClass.mapParts(
//            WeaponGenerator.ARROW_HEADS_KEY, WeaponGenerator.ARROW_SHAFTS_KEY, WeaponGenerator.ARROW_FLETCHINGS_KEY),
//            w -> {
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.ARROW_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase shaft = w.getArmorParts().get(WeaponGenerator.ARROW_SHAFTS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase flight = w.getArmorParts().get(WeaponGenerator.ARROW_FLETCHINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", flight.getNameMod(), head.getNameMod(), shaft.getNameMod());
//            });
//    public static final  WeaponClass       WEAPON_CLASS_STAFF    = new WeaponClass("Staff", WeaponClass.mapParts(
//            WeaponGenerator.STAFF_HANDLES_KEY, WeaponGenerator.STAFF_HEADS_KEY),
//            w -> {
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.STAFF_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase handle = w.getArmorParts().get(WeaponGenerator.STAFF_HANDLES_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s", head.getNameMod(), handle.getNameMod());
//            });
//    public static final  WeaponClass       WEAPON_CLASS_CROSSBOW = new WeaponClass("Crossbow", WeaponClass.mapParts(
//            WeaponGenerator.CROSSBOW_STOCKS_KEY, WeaponGenerator.CROSSBOW_LIMBS_KEY, WeaponGenerator.CROSSBOW_STRINGS_KEY),
//            w -> {
//                WeaponPartBase stock = w.getArmorParts().get(WeaponGenerator.CROSSBOW_STOCKS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase limbs = w.getArmorParts().get(WeaponGenerator.CROSSBOW_LIMBS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase string = w.getArmorParts().get(WeaponGenerator.CROSSBOW_STRINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", string.getNameMod(), stock.getNameMod(), limbs.getNameMod());
//            });
//    public static final  WeaponClass       WEAPON_CLASS_BOLT    = new WeaponClass("Bolt", WeaponClass.mapParts(
//            WeaponGenerator.BOLT_HEADS_KEY, WeaponGenerator.BOLT_SHAFTS_KEY, WeaponGenerator.BOLT_FLETCHINGS_KEY),
//            w -> {
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.BOLT_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase shaft = w.getArmorParts().get(WeaponGenerator.BOLT_SHAFTS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase flight = w.getArmorParts().get(WeaponGenerator.BOLT_FLETCHINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", flight.getNameMod(), head.getNameMod(), shaft.getNameMod());
//            });

    private final HashMap<String, List<WeaponPart>> weaponParts;
    private final String                            weaponClassName;
    private final int                               enchantmentMin;
    private final int                               enchantmentMax;
    private final float                             enchantmentChance;
    private final StatSet                           statSet;

    public WeaponClass(String name, HashMap<String, List<WeaponPart>> map) {
        this(name, 0, 2, 0.15F, StatSet.DEFAULT, map);
    }

    public WeaponClass(String name, int min, int max, float chance, StatSet stats, HashMap<String, List<WeaponPart>> map) {
        this.weaponClassName = name;
        this.weaponParts = map;
        this.enchantmentMin = min;
        this.enchantmentMax = max;
        this.enchantmentChance = chance;
        this.statSet = stats;
        WEAPON_CLASSES.add(this);
    }
    public static HashMap<String, List<WeaponPart>> mapParts(String... keys) {
        HashMap<String, List<WeaponPart>> map = new HashMap<>();
        for (String key : keys) {
            map.put(key, WeaponGenerator.getWeaponParts(key));
        }
        return map;
    }

    public float getEnchantmentChance() {
        return enchantmentChance;
    }

    public int getEnchantmentMax() {
        return enchantmentMax;
    }

    public int getEnchantmentMin() {
        return enchantmentMin;
    }

    public String getWeaponClassName() {
        return weaponClassName;
    }

    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet determineStats(WeaponInstance weaponInstance) {
        return this.statSet;
    }

    @Override
    public float getWeight() {
        return 1F;
    }

    public HashMap<String, List<WeaponPart>> getWeaponPartLists() {
        return this.weaponParts;
    }

    //    public abstract Generator<? extends WeaponInstance> getGenerator();
    public static List<WeaponClass> getWeaponClasses() {
        return WEAPON_CLASSES;
    }

}