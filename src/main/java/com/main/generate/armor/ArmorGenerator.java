package com.main.generate.armor;

import com.main.generate.Generator;
import com.main.nbt.NBTTagCompound;
import com.main.util.WeightedRandomFloat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class ArmorGenerator implements Generator<ArmorInstance> { //Generator<Sword> {

    public static final String MELEE_HELMET_BASE_KEY = "meleeHelmetBase";
    public static final String MELEE_HELMET_ADD_KEY  = "meleeHelmetAddition";

    private static final HashMap<String, List<ArmorPart>> armorPartsLists = new HashMap<>();

    public static final List<ArmorPart> MELEE_HELMET_BASES = getArmorParts(MELEE_HELMET_BASE_KEY);
    public static final List<ArmorPart> MELEE_HELMET_ADDS  = getArmorParts(MELEE_HELMET_ADD_KEY);

    public static List<ArmorPart> getArmorParts(String key) {
        List<ArmorPart> parts = armorPartsLists.get(key);
        if (parts == null) {
            parts = new ArrayList<>();
            armorPartsLists.put(key, parts);
        }
        return parts;
    }

    @Override
    public ArmorInstance generate(Random r, int level) {
        ArmorClass armorClass = WeightedRandomFloat.getRandomItem(r, ArmorClass.getArmorClasses());
        return this.generate(r, level, armorClass);
    }
    public ArmorInstance generate(Random r, int level, ArmorClass armorClass) {
        HashMap<String, List<ArmorPart>> partMap = armorClass.getArmorPartsLists();
        HashMap<String, ArmorPartInstance> partInstances = new HashMap<>();

        for (String key : partMap.keySet()) {
            List<ArmorPart> parts = partMap.get(key);
            ArmorPart part = null;//WeightedRandomFloat.getRandomItem(r, parts);
            ArmorPartInstance instance = new ArmorPartInstance(part, level);
            partInstances.put(key, instance);
        }
        ArmorInstance armor = new ArmorInstance(armorClass, level, partInstances);
        return armor;
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
        for(String key : armorPartsLists.keySet()) {
            List<ArmorPart> parts = armorPartsLists.get(key);
            System.out.println(parts.size() + " " + key);
        }
        DecimalFormat format = new DecimalFormat("#,###");
        long total = 0;
        for(ArmorClass armorClass : ArmorClass.getArmorClasses()) {
            HashMap<String, List<ArmorPart>> parts = armorClass.getArmorPartsLists();
            if(parts != null) {
                long[] sub = {1};
                parts.forEach((s, l) -> sub[0] *= l.size());
                System.out.println(format.format(sub[0]) + " total " + armorClass.getArmorClassName() + "s");
                total += sub[0];
            }
        }
        System.out.println(format.format(ArmorPartBase.getBaseParts().size()) + " total base parts");
        System.out.println(format.format(total) + " total armor pieces available!");
    }
    public static List<ArmorPart> getAll() {
        List<ArmorPart> ret = new ArrayList<>();
        armorPartsLists.values().forEach(i -> ret.addAll(i));
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
