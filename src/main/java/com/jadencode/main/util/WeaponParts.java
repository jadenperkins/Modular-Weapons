package com.jadencode.main.util;

import com.jadencode.main.TimeKeeper;
import com.jadencode.main.generate.weapon.WeaponClass;
import com.jadencode.main.generate.weapon.WeaponPart;
import com.jadencode.main.generate.weapon.WeaponPartBase;
import com.jadencode.main.constants.WeaponPartType;

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
    private static final HashMap<WeaponPartType, List<WeaponPart>> partsLists = new HashMap<>();

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
        for(WeaponClass weaponClass : WeaponClass.getWeaponClasses()) {
            long sub = 1;
            for(WeaponPartType type : weaponClass.getWeaponPartTypes()) {
                List<WeaponPart> parts = getPartsList(type);
                if(parts != null && !parts.isEmpty()) {
                    sub *= parts.size();
                    System.out.println(format.format(sub) + " total " + weaponClass.getWeaponClassName() + "s");
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