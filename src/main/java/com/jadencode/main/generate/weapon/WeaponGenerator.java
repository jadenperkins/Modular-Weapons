package com.jadencode.main.generate.weapon;

import com.jadencode.main.generate.Generator;
import com.jadencode.main.nbt.NBTTagCompound;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.util.WeightedRandomFloat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class WeaponGenerator implements Generator<WeaponInstance> { //Generator<Sword> {

    public static final String SWORD_GRIPS_KEY      = "swordGrips";
    public static final String SWORD_HILTS_KEY      = "swordHilts";
    public static final String SWORD_BLADES_KEY     = "swordBlades";
    public static final String HAMMER_HEADS_KEY     = "hammerHeads";
    public static final String HAMMER_HANDLES_KEY   = "hammerHandles";
    public static final String AXE_HEADS_KEY        = "axeHeads";
    public static final String AXE_HANDLES_KEY      = "axeHandles";
    public static final String BOW_STRINGS_KEY      = "bowStrings";
    public static final String BOW_LIMBS_KEY        = "bowLimbs";
    public static final String BOW_GRIPS_KEY        = "bowGrips";
    public static final String ARROW_HEADS_KEY      = "arrowHeads";
    public static final String ARROW_SHAFTS_KEY     = "arrowShafts";
    public static final String ARROW_FLETCHINGS_KEY = "arrowFletchings";
    public static final String STAFF_HANDLES_KEY    = "staffHandles";
    public static final String STAFF_HEADS_KEY      = "staffHeads";
    public static final String CROSSBOW_STOCKS_KEY  = "crossbowStocks";
    public static final String CROSSBOW_LIMBS_KEY   = "crossbowLimbs";
    public static final String CROSSBOW_STRINGS_KEY = "crossbowStrings";
    public static final String BOLT_HEADS_KEY       = "boltHeads";
    public static final String BOLT_SHAFTS_KEY      = "boltShafts";
    public static final String BOLT_FLETCHINGS_KEY  = "boltFletchings";

    private static final HashMap<String, List<WeaponPart>> weaponPartsLists = new HashMap<>();

    public static final List<WeaponPart> SWORD_GRIPS  = getWeaponParts(SWORD_GRIPS_KEY);
    public static final List<WeaponPart> SWORD_HILTS  = getWeaponParts(SWORD_HILTS_KEY);
    public static final List<WeaponPart> SWORD_BLADES = getWeaponParts(SWORD_BLADES_KEY);

    public static final List<WeaponPart> HAMMER_HEADS   = getWeaponParts(HAMMER_HEADS_KEY);
    public static final List<WeaponPart> HAMMER_HANDLES = getWeaponParts(HAMMER_HANDLES_KEY);

    public static final List<WeaponPart> AXE_HEADS   = getWeaponParts(AXE_HEADS_KEY);
    public static final List<WeaponPart> AXE_HANDLES = getWeaponParts(AXE_HANDLES_KEY);

    public static final List<WeaponPart> BOW_STRINGS = getWeaponParts(BOW_STRINGS_KEY);
    public static final List<WeaponPart> BOW_LIMBS   = getWeaponParts(BOW_LIMBS_KEY);
    public static final List<WeaponPart> BOW_GRIPS   = getWeaponParts(BOW_GRIPS_KEY);

    public static final List<WeaponPart> ARROW_HEADS      = getWeaponParts(ARROW_HEADS_KEY);
    public static final List<WeaponPart> ARROW_SHAFTS     = getWeaponParts(ARROW_SHAFTS_KEY);
    public static final List<WeaponPart> ARROW_FLETCHINGS = getWeaponParts(ARROW_FLETCHINGS_KEY);

    public static final List<WeaponPart> STAFF_HANDLES = getWeaponParts(STAFF_HANDLES_KEY);
    public static final List<WeaponPart> STAFF_HEADS   = getWeaponParts(STAFF_HEADS_KEY);

    public static final List<WeaponPart> CROSSBOW_STOCKS  = getWeaponParts(CROSSBOW_STOCKS_KEY);
    public static final List<WeaponPart> CROSSBOW_LIMBS   = getWeaponParts(CROSSBOW_LIMBS_KEY);
    public static final List<WeaponPart> CROSSBOW_STRINGS = getWeaponParts(CROSSBOW_STRINGS_KEY);

    public static final List<WeaponPart> BOLT_HEADS      = getWeaponParts(BOLT_HEADS_KEY);
    public static final List<WeaponPart> BOLT_SHAFTS     = getWeaponParts(BOLT_SHAFTS_KEY);
    public static final List<WeaponPart> BOLT_FLETCHINGS = getWeaponParts(BOLT_FLETCHINGS_KEY);

    public static List<WeaponPart> getWeaponParts(String key) {
        List<WeaponPart> parts = weaponPartsLists.get(key);
        if (parts == null) {
            parts = new ArrayList<>();
            weaponPartsLists.put(key, parts);
        }
        return parts;
    }

    @Override
    public WeaponInstance generate(Random r, int level) {
        WeaponClass weaponClass = WeightedRandomFloat.getRandomWeightable(r, WeaponClass.getWeaponClasses());
        return this.generate(r, level, weaponClass);
    }
    public WeaponInstance generate(Random r, int level, WeaponClass weaponClass) {
        HashMap<String, List<WeaponPart>> partMap = weaponClass.getWeaponPartLists();
        HashMap<String, WeaponPartInstance> partInstances = new HashMap<>();

        for (String key : partMap.keySet()) {
            List<WeaponPart> parts = partMap.get(key);
            WeaponPart part = WeightedRandomFloat.getRandomWeightable(r, parts);
            WeaponPartInstance instance = new WeaponPartInstance(part, level);
            partInstances.put(key, instance);
            System.out.println(String.format("Weapon part %s created, slash damage is %f", instance.getWeaponPart().getPartName(), instance.getStatSet().get(StatBase.DAMAGE_SLASH)));
        }
        WeaponInstance weapon = new WeaponInstance(weaponClass, level, partInstances);

        return weapon;
    }


//        Generator<String> generator = Weapon.getRandomClass(r).getGenerator();
//        return generator.generate(r, level);

//        String pommel = RandomUtil.random(GRIP_WORD, r);
//        String blade = RandomUtil.random(BLADE_WORD, r);
//        String guard = RandomUtil.random(GUARD_WORD, r);
//
//        String name = String.format("%s %s%s", pommel, blade, guard);
//
//        return name + (r.nextInt(6) == 0 ? " of " + RandomUtil.random(ENCHANT_WORD, r) : "");
//    }
    public static void countParts() {
        for(String key : weaponPartsLists.keySet()) {
            List<WeaponPart> parts = weaponPartsLists.get(key);
            System.out.println(parts.size() + " " + key);
        }
        DecimalFormat format = new DecimalFormat("#,###");
        long total = 0;
        for(WeaponClass weaponClass : WeaponClass.getWeaponClasses()) {
            HashMap<String, List<WeaponPart>> parts = weaponClass.getWeaponPartLists();
            if(parts != null) {
                long[] sub = {1};
                parts.forEach((s, l) -> sub[0] *= l.size());
                System.out.println(format.format(sub[0]) + " total " + weaponClass.getWeaponClassName() + "s");
                total += sub[0];
            }
        }
        System.out.println(format.format(WeaponPartBase.getBaseParts().size()) + " total base parts");
        System.out.println(format.format(total) + " total weapons available!");
    }
    public static List<WeaponPart> getAll() {
        List<WeaponPart> ret = new ArrayList<>();
        weaponPartsLists.values().forEach(i -> ret.addAll(i));
        return ret;
    }
    @Override
    public void readNBT(NBTTagCompound nbt) {

    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {

    }

    @Override
    public void onCreated(Random r) {

    }
}
