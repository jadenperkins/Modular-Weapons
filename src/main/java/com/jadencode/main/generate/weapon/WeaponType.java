package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponTypes;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponType implements WeightedItem {
//    public static final  WeaponType       WEAPON_CLASS_HAMMER   = new WeaponType("Hammer", WeaponType.mapParts(
//            WeaponGenerator.HAMMER_HANDLES_KEY, WeaponGenerator.HAMMER_HEADS_KEY),
//            w -> {
//                WeaponPartBase handle = w.getArmorParts().get(WeaponGenerator.HAMMER_HANDLES_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.HAMMER_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s", head.getNameMod(), handle.getNameMod());
//            });
//    public static final  WeaponType       WEAPON_CLASS_AXE      = new WeaponType("Axe", WeaponType.mapParts(
//            WeaponGenerator.AXE_HANDLES_KEY, WeaponGenerator.AXE_HEADS_KEY),
//            w -> {
//                WeaponPartBase handle = w.getArmorParts().get(WeaponGenerator.AXE_HANDLES_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.AXE_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s", head.getNameMod(), handle.getNameMod());
//            });
//    public static final  WeaponType       WEAPON_CLASS_BOW      = new WeaponType("Bow", WeaponType.mapParts(
//            WeaponGenerator.BOW_GRIPS_KEY, WeaponGenerator.BOW_LIMBS_KEY, WeaponGenerator.BOW_STRINGS_KEY),
//            w -> {
//                WeaponPartBase grip = w.getArmorParts().get(WeaponGenerator.BOW_GRIPS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase limbs = w.getArmorParts().get(WeaponGenerator.BOW_LIMBS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase string = w.getArmorParts().get(WeaponGenerator.BOW_STRINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", string.getNameMod(), grip.getNameMod(), limbs.getNameMod());
//            });
//    public static final  WeaponType       WEAPON_CLASS_ARROW    = new WeaponType("Arrow", WeaponType.mapParts(
//            WeaponGenerator.ARROW_HEADS_KEY, WeaponGenerator.ARROW_SHAFTS_KEY, WeaponGenerator.ARROW_FLETCHINGS_KEY),
//            w -> {
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.ARROW_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase shaft = w.getArmorParts().get(WeaponGenerator.ARROW_SHAFTS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase flight = w.getArmorParts().get(WeaponGenerator.ARROW_FLETCHINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", flight.getNameMod(), head.getNameMod(), shaft.getNameMod());
//            });
//    public static final  WeaponType       WEAPON_CLASS_STAFF    = new WeaponType("Staff", WeaponType.mapParts(
//            WeaponGenerator.STAFF_HANDLES_KEY, WeaponGenerator.STAFF_HEADS_KEY),
//            w -> {
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.STAFF_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase handle = w.getArmorParts().get(WeaponGenerator.STAFF_HANDLES_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s", head.getNameMod(), handle.getNameMod());
//            });
//    public static final  WeaponType       WEAPON_CLASS_CROSSBOW = new WeaponType("Crossbow", WeaponType.mapParts(
//            WeaponGenerator.CROSSBOW_STOCKS_KEY, WeaponGenerator.CROSSBOW_LIMBS_KEY, WeaponGenerator.CROSSBOW_STRINGS_KEY),
//            w -> {
//                WeaponPartBase stock = w.getArmorParts().get(WeaponGenerator.CROSSBOW_STOCKS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase limbs = w.getArmorParts().get(WeaponGenerator.CROSSBOW_LIMBS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase string = w.getArmorParts().get(WeaponGenerator.CROSSBOW_STRINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", string.getNameMod(), stock.getNameMod(), limbs.getNameMod());
//            });
//    public static final  WeaponType       WEAPON_CLASS_BOLT    = new WeaponType("Bolt", WeaponType.mapParts(
//            WeaponGenerator.BOLT_HEADS_KEY, WeaponGenerator.BOLT_SHAFTS_KEY, WeaponGenerator.BOLT_FLETCHINGS_KEY),
//            w -> {
//                WeaponPartBase head = w.getArmorParts().get(WeaponGenerator.BOLT_HEADS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase shaft = w.getArmorParts().get(WeaponGenerator.BOLT_SHAFTS_KEY).getArmorPart().getBaseArmorPart();
//                WeaponPartBase flight = w.getArmorParts().get(WeaponGenerator.BOLT_FLETCHINGS_KEY).getArmorPart().getBaseArmorPart();
//                return String.format("%s %s %s", flight.getNameMod(), head.getNameMod(), shaft.getNameMod());
//            });

    private final String weaponTypeName;
    private final StatSet                           statSet;
    private final List<WeaponPartType>              weaponPartTypes;
    private final Function<WeaponInstance, String>  nameGenerator;
    private final float                             weight;

    public WeaponType(String name, StatSet stats, List<WeaponPartType> types, Function<WeaponInstance, String> g) {
        this(name, 1F, stats, types, g);
    }
    public WeaponType(String name, float w, StatSet stats, List<WeaponPartType> types, Function<WeaponInstance, String> g) {
        this.weaponTypeName = name;
        this.weight = w;
        this.weaponPartTypes = types;
        this.statSet = stats;
        this.nameGenerator = g;
        WeaponTypes.addWeaponType(this);
    }
    public String getDisplayName(WeaponInstance weapon) {
        return this.nameGenerator.apply(weapon);
    }
    public String getWeaponTypeName() {
        return weaponTypeName;
    }

    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet determineStats(WeaponInstance instance) {
        List<StatSet> others = instance.getWeaponParts().values().stream().map(WeaponPartInstance::getStats).collect(Collectors.toList());
        StatSet baseStats = this.getStatSet().scaled(instance.getLevel()).combine(others);
        return baseStats;
    }
    public List<WeaponPartType> getWeaponPartTypes() {
        return this.weaponPartTypes;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }
}