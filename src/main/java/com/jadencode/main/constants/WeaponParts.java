package com.jadencode.main.constants;

import com.jadencode.main.TimeKeeper;
import com.jadencode.main.generate.weapon.WeaponType;
import com.jadencode.main.generate.weapon.WeaponPart;
import com.jadencode.main.generate.weapon.WeaponPartBase;
import com.jadencode.main.generate.weapon.WeaponPartType;
import com.jadencode.main.material.MaterialLibrary;

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

    public static final WeaponPartType PART_SWORD_HILT  = new WeaponPartType("sword_hilt");
    public static final WeaponPartType PART_SWORD_GRIP  = new WeaponPartType("sword_grip");
    public static final WeaponPartType PART_SWORD_BLADE = new WeaponPartType("sword_blade");

    //Common Grips
    public static final WeaponPartBase heavyGrip = new WeaponPartBase("Heavy Grip", "Double", StatSets.GRIP_HEAVY, WeaponParts.PART_SWORD_GRIP, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase basicGrip = new WeaponPartBase("Basic Grip", "Single", StatSets.GRIP_BASIC, WeaponParts.PART_SWORD_GRIP, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase lightGrip = new WeaponPartBase("Light Grip", "Half", StatSets.GRIP_LIGHT, WeaponParts.PART_SWORD_GRIP, MaterialLibrary.getMetalLibrary());

    //Common Hilts
    public static final WeaponPartBase heavyHilt  = new WeaponPartBase("Heavy Hilt", "Heavy", StatSets.HILT_HEAVY, WeaponParts.PART_SWORD_HILT, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase mediumHilt = new WeaponPartBase("Balanced Hilt", "Balanced", StatSets.HILT_MEDIUM, WeaponParts.PART_SWORD_HILT, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase lightHilt  = new WeaponPartBase("Light Hilt", "Agile", StatSets.HILT_LIGHT, WeaponParts.PART_SWORD_HILT, MaterialLibrary.getMetalLibrary());

    //Common Blades
    public static final WeaponPartBase broadBlade = new WeaponPartBase("Broad Blade", "Broadsword", StatSets.BLADE_BROAD, WeaponParts.PART_SWORD_BLADE, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase longBlade  = new WeaponPartBase("Long Blade", "Longsword", StatSets.BLADE_LONG, WeaponParts.PART_SWORD_BLADE, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase shortBlade = new WeaponPartBase("Short Blade", "Shortsword", StatSets.BLADE_SHORT, WeaponParts.PART_SWORD_BLADE, MaterialLibrary.getMetalLibrary());

    public static List<WeaponPartBase> getBaseParts() {
        return WEAPON_PARTS;
    }
    public static void addBasePart(WeaponPartBase part) {
        WEAPON_PARTS.add(part);
    }
    public static void generateWeaponParts() {
        WeaponParts.getBaseParts()
                .forEach(partBase -> partBase.getMaterials()
                        .forEach(res -> new WeaponPart(partBase, res)));
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
                    System.out.println(format.format(sub) + " total " + weaponType.getWeaponClassName() + "s");
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
            if(part.hasBaseResource()) {
                msg += part.getBaseResource().getName();
            } else {
                msg += "No Resource";
            }
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