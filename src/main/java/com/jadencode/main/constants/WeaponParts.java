package com.jadencode.main.constants;

import com.jadencode.main.TimeKeeper;
import com.jadencode.main.generate.weapon.WeaponType;
import com.jadencode.main.generate.weapon.WeaponPart;
import com.jadencode.main.generate.weapon.WeaponPartBase;
import com.jadencode.main.generate.weapon.WeaponPartType;

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
    public static final WeaponPartType PART_SWORD_HILT  = new WeaponPartType("sword_hilt");
    public static final WeaponPartType PART_SWORD_GRIP  = new WeaponPartType("sword_grip");
    public static final WeaponPartType PART_SWORD_BLADE = new WeaponPartType("sword_blade");

    private static final HashMap<WeaponPartType, List<WeaponPart>> partsLists = new HashMap<>();

    public static void generateWeaponParts() {
        WeaponPartBase.getBaseParts()
                .forEach(partBase -> partBase.getMaterials()
                        .forEach(res -> new WeaponPart(partBase, res)));
    }

    public static List<WeaponPart> getPartsList(WeaponPartType type) {
        if(partsLists.containsKey(type)) return partsLists.get(type);
        List<WeaponPart> parts = new ArrayList<>();
        partsLists.put(type, parts);
        return parts;
    }
    public static void countParts() {
        for(WeaponPartType type : partsLists.keySet()) {
            List<WeaponPart> parts = partsLists.get(type);
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
        System.out.println(format.format(WeaponPartBase.getBaseParts().size()) + " total base parts");
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
        partsLists.values().forEach(ret::addAll);
        return ret;
    }
}