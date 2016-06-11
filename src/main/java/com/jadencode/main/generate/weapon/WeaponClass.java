package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponPartType;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.util.Weightable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponClass implements Weightable {

    private static final List<WeaponClass> WEAPON_CLASSES     = new ArrayList<>();
    public static final  WeaponClass       WEAPON_CLASS_SWORD = new WeaponClass("Sword", StatSets.SWORD_BASE_STATS,
            Arrays.asList(WeaponPartType.PART_SWORD_HILT, WeaponPartType.PART_SWORD_GRIP, WeaponPartType.PART_SWORD_BLADE));


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

    private final String                            weaponClassName;
    private final StatSet                           statSet;
    private final List<WeaponPartType>              weaponPartTypes;

    public WeaponClass(String name, StatSet stats, List<WeaponPartType> types) {
        this.weaponClassName = name;
        this.weaponPartTypes = types;
        this.statSet = stats;

        WEAPON_CLASSES.add(this);
    }

    public String getWeaponClassName() {
        return weaponClassName;
    }

    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet determineStats(WeaponInstance instance) {
        List<StatSet> others = instance.getWeaponParts().values().stream().map(WeaponPartInstance::getStatSet).collect(Collectors.toList());
        StatSet baseStats = this.getStatSet().scaled(instance.getLevel()).combine(others);
        return baseStats;
    }
    public List<WeaponPartType> getWeaponPartTypes() {
        return this.weaponPartTypes;
    }

    @Override
    public float getWeight() {
        return 1F;
    }
    //    public abstract Generator<? extends WeaponInstance> getGenerator();

    public static List<WeaponClass> getWeaponClasses() {
        return WEAPON_CLASSES;
    }

}