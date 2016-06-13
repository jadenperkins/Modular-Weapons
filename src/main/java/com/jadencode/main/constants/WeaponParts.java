package com.jadencode.main.constants;

import com.jadencode.main.TimeKeeper;
import com.jadencode.main.generate.weapon.*;
import com.jadencode.main.material.MaterialType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class WeaponParts {
    private static final List<WeaponPartBase> WEAPON_PARTS = new ArrayList<>();
    private static final HashMap<WeaponPartType, List<WeaponPart>> PARTS_LISTS = new HashMap<>();

    //Some legendary parts
    public static final WeaponPart ascendantHilt = new WeaponPartLegendary("Hilt of Ascension", "Ascendant", 100F, PartTypes.PART_SWORD_HILT);
    public static final WeaponPart darkGrip      = new WeaponPartLegendary("Grip of Darkness", "Dark", 100F, PartTypes.PART_SWORD_GRIP);
    public static final WeaponPart seekerBlade   = new WeaponPartLegendary("Seeker Blade", "Seeker", 100F, PartTypes.PART_SWORD_BLADE);

    //Common Grips
    public static final WeaponPartBase heavyGrip = new WeaponPartBase("Heavy Grip", "Double", StatSets.GRIP_HEAVY, PartTypes.PART_SWORD_GRIP, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase basicGrip = new WeaponPartBase("Basic Grip", "Single", StatSets.GRIP_BASIC, PartTypes.PART_SWORD_GRIP, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase lightGrip = new WeaponPartBase("Light Grip", "Half", StatSets.GRIP_LIGHT, PartTypes.PART_SWORD_GRIP, MaterialTypes.MATERIAL_METAL);

    //Common Hilts
    public static final WeaponPartBase heavyHilt  = new WeaponPartBase("Heavy Hilt", "Heavy", StatSets.HILT_HEAVY, PartTypes.PART_SWORD_HILT, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase mediumHilt = new WeaponPartBase("Balanced Hilt", "Balanced", StatSets.HILT_MEDIUM, PartTypes.PART_SWORD_HILT, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase lightHilt  = new WeaponPartBase("Light Hilt", "Agile", StatSets.HILT_LIGHT, PartTypes.PART_SWORD_HILT, MaterialTypes.MATERIAL_METAL);

    //Common Blades
    public static final WeaponPartBase broadBlade = new WeaponPartBase("Broad Blade", "Broadsword", StatSets.BLADE_BROAD, PartTypes.PART_SWORD_BLADE, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase longBlade  = new WeaponPartBase("Long Blade", "Longsword", StatSets.BLADE_LONG, PartTypes.PART_SWORD_BLADE, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase shortBlade = new WeaponPartBase("Short Blade", "Shortsword", StatSets.BLADE_SHORT, PartTypes.PART_SWORD_BLADE, MaterialTypes.MATERIAL_METAL);



    //Common Hammer Handles
    public static final WeaponPartBase warHandleHammer    = new WeaponPartBase("War Handle", "Warhammer", StatSets.HANDLE_WAR_HAMMER, PartTypes.PART_HAMMER_HANDLE, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase battleHandleHammer = new WeaponPartBase("Battle Handle", "Battlehammer", StatSets.HANDLE_BATTLE_HAMMER, PartTypes.PART_HAMMER_HANDLE, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase maulHandleHammer   = new WeaponPartBase("Maul Handle", "Maul", StatSets.HANDLE_MAUL_HAMMER, PartTypes.PART_HAMMER_HANDLE, MaterialTypes.MATERIAL_WOOD);

    //Common Hammer Heads
    public static final WeaponPartBase doubleHeadHammer = new WeaponPartBase("Double Hammerhead", "Double-Headed", StatSets.HEAD_DOUBLE_HAMMER, PartTypes.PART_HAMMER_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase singleHeadHammer = new WeaponPartBase("Single Hammerhead", "Single-Headed", StatSets.HEAD_SINGLE_HAMMER, PartTypes.PART_HAMMER_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase heavyHeadHammer  = new WeaponPartBase("Heavy Hammerhead", "Heavy", StatSets.HEAD_HEAVY_HAMMER, PartTypes.PART_HAMMER_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase spikedHeadHammer = new WeaponPartBase("Spiked Hammerhead", "Spike-Headed", StatSets.HEAD_SPIKED_HAMMER, PartTypes.PART_HAMMER_HEAD, MaterialTypes.MATERIAL_METAL);



    //Common Axe Handles
    public static final WeaponPartBase warHandleAxe     = new WeaponPartBase("War Handle", "Waraxe", StatSets.HANDLE_WAR_AXE, PartTypes.PART_AXE_HANDLE, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase longHandleAxe    = new WeaponPartBase("Long Handle", "Longaxe", StatSets.HANDLE_LONG_AXE, PartTypes.PART_AXE_HANDLE, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase battleHandleAxe  = new WeaponPartBase("Battle Handle", "Battleaxe", StatSets.HANDLE_BATTLE_AXE, PartTypes.PART_AXE_HANDLE, MaterialTypes.MATERIAL_WOOD);

    //Common Axe Heads
    public static final WeaponPartBase doubleHead = new WeaponPartBase("Double Axehead", "Double-Headed", StatSets.HEAD_DOUBLE_AXE, PartTypes.PART_AXE_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase singleHead = new WeaponPartBase("Single Axehead", "Single-Headed", StatSets.HEAD_SINGLE_AXE, PartTypes.PART_AXE_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase broadHead  = new WeaponPartBase("Broad Axehead", "Broad-Headed", StatSets.HEAD_BROAD_AXE, PartTypes.PART_AXE_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase spikeHead  = new WeaponPartBase("Spiked Axehead", "Spike-Headed", StatSets.HEAD_SPIKED_AXE, PartTypes.PART_AXE_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase scytheHead = new WeaponPartBase("Scythe Axehead", "Scythe-Headed", StatSets.HEAD_SCYTHE_AXE, PartTypes.PART_AXE_HEAD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase roundHead  = new WeaponPartBase("Round Axehead", "Round-Headed", StatSets.HEAD_ROUND_AXE, PartTypes.PART_AXE_HEAD, MaterialTypes.MATERIAL_METAL);



    //Common Bow Strings
    public static final WeaponPartBase lightString = new WeaponPartBase("Light Bowstring", "Light", StatSets.STRING_LIGHT_BOW, PartTypes.PART_BOW_STRING, MaterialTypes.MATERIAL_PLANT);
    public static final WeaponPartBase heavyString = new WeaponPartBase("Heavy Bowstring", "Heavy", StatSets.STRING_HEAVY_BOW, PartTypes.PART_BOW_STRING, MaterialTypes.MATERIAL_PLANT);

    //Common Bow Limbs
    public static final WeaponPartBase shortLimbs  = new WeaponPartBase("Shortbow Limbs", "Short Bow", StatSets.LIMBS_SHORT_BOW, PartTypes.PART_BOW_LIMBS, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase longLimbs   = new WeaponPartBase("Longbow Limbs", "Long Bow", StatSets.LIMBS_LONG_BOW, PartTypes.PART_BOW_LIMBS, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase mediumLimbs = new WeaponPartBase("Recurve Limbs", "Recurve Bow", StatSets.LIMBS_MEDIUM_BOW, PartTypes.PART_BOW_LIMBS, MaterialTypes.MATERIAL_WOOD);

    //Common Bow Grips
    public static final WeaponPartBase quickGrip    = new WeaponPartBase("Slick Bow Grip", "Rapid", StatSets.GRIP_QUICK_BOW, PartTypes.PART_BOW_GRIP, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase steadyGrip   = new WeaponPartBase("Steady Bow Grip", "Accurate", StatSets.GRIP_STEADY_BOW, PartTypes.PART_BOW_GRIP, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase balancedGrip = new WeaponPartBase("Balanced Bow Grip", "Balanced", StatSets.GRIP_BALANCED_BOW, PartTypes.PART_BOW_GRIP, MaterialTypes.MATERIAL_METAL);



    //Common Crossbow Stocks
    public static final WeaponPartBase accurateStock = new WeaponPartBase("Accurate Stock", "Accurate", StatSets.STOCK_ACCURATE_CBOW, PartTypes.PART_CBOW_STOCK, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase balancedStock = new WeaponPartBase("Balanced Stock", "Balanced", StatSets.STOCK_BALANCED_CBOW, PartTypes.PART_CBOW_STOCK, MaterialTypes.MATERIAL_WOOD);
    public static final WeaponPartBase rapidStock    = new WeaponPartBase("Rapid Stock", "Rapid", StatSets.STOCK_RAPID_CBOW, PartTypes.PART_CBOW_STOCK, MaterialTypes.MATERIAL_WOOD);

    //Common Crossbow Limbs
    public static final WeaponPartBase shortCrossbowLimbs = new WeaponPartBase("Light Limbs", "Light Crossbow", StatSets.LIMBS_LIGHT_CBOW, PartTypes.PART_CBOW_LIMBS, MaterialTypes.MATERIAL_WOOD, MaterialTypes.MATERIAL_METAL);
    public static final WeaponPartBase longCrossbowLimbs  = new WeaponPartBase("Heavy Limbs", "Heavy Crossbow", StatSets.LIMBS_HEAVY_CBOW, PartTypes.PART_CBOW_LIMBS, MaterialTypes.MATERIAL_WOOD, MaterialTypes.MATERIAL_METAL);

    //Common Crossbow Strings
    public static final WeaponPartBase heavyCrossbowString = new WeaponPartBase("Heavy Crossbow String", "High-Draw", StatSets.STRING_HEAVY_CBOW, PartTypes.PART_CBOW_STRING, MaterialTypes.MATERIAL_PLANT);
    public static final WeaponPartBase lightCrossbowString = new WeaponPartBase("Light Crossbow String", "Low-Draw", StatSets.STRING_LIGHT_CBOW, PartTypes.PART_CBOW_STRING, MaterialTypes.MATERIAL_PLANT);




    public static List<WeaponPartBase> getBaseParts() {
        return WEAPON_PARTS;
    }
    public static void addBasePart(WeaponPartBase part) {
        WEAPON_PARTS.add(part);
    }
    public static void generateWeaponParts() {
        WeaponParts.getBaseParts()
                .forEach(partBase -> partBase.getMaterials()
                        .forEach(res -> new WeaponPartBasic(partBase, res)));
    }

    public static List<WeaponPart> getPartsList(WeaponPartType type) {
        if(PARTS_LISTS.containsKey(type)) return PARTS_LISTS.get(type);
        List<WeaponPart> parts = new ArrayList<>();
        PARTS_LISTS.put(type, parts);
        return parts;
    }
    public static void countParts() {
        for(WeaponPartType type : PARTS_LISTS.keySet()) {
            List<WeaponPart> parts = PARTS_LISTS.get(type);
            System.out.println(parts.size() + " " + type.getTypeName());
        }
        DecimalFormat format = new DecimalFormat("#,###");
        long total = 0;
        for(WeaponType weaponType : WeaponTypes.getWeaponTypes()) {
            long sub = 1;
            for(WeaponPartType type : weaponType.getWeaponPartTypes()) {
                List<WeaponPart> parts = getPartsList(type);
                if(parts != null && !parts.isEmpty()) {
                    sub *= parts.size();
                    System.out.println(format.format(sub) + " total " + weaponType.getWeaponTypeName() + "s");
                    total += sub;
                }
            }
        }
        System.out.println(format.format(WeaponParts.getBaseParts().size()) + " total base parts");
        System.out.println(format.format(total) + " total weapons available!");
    }
    public static void enumerateParts(boolean storeFile) {
        TimeKeeper timer = new TimeKeeper();
        timer.start("Enumeration all weapon parts. . .");

        List<WeaponPart> parts = getAll();
        parts.sort((a, b) -> a.getPartName().compareTo(b.getPartName()));

        PrintStream writer = System.out;

        if(storeFile) {
            File out = new File("./enum.txt");

            try {
                out.createNewFile();
                writer = new PrintStream(new FileOutputStream(out));

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        for(WeaponPart part : parts) {
            String msg = String.format("%s, %s\n\t", part.getPartName(), part.getPartInfo());
            writer.println(msg);
        }
        writer.close();
        timer.stopAndDisplay();
    }
    public static List<WeaponPart> getAll() {
        List<WeaponPart> ret = new ArrayList<>();
        PARTS_LISTS.values().forEach(ret::addAll);
        return ret;
    }
}