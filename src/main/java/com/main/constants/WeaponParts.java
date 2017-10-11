package com.main.constants;

import com.main.TimeKeeper;
import com.main.generate.weapon.*;
import com.main.material.Material;
import com.main.material.MaterialType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class WeaponParts {
    private static final List<WeaponPartBase> WEAPON_PARTS = new ArrayList<>();
    private static final HashMap<WeaponPartType, List<WeaponPart>> PARTS_LISTS = new HashMap<>();
    private static final HashMap<String, WeaponPart> MAPPED_PARTS = new HashMap<>();

    public static void register(WeaponPart part) {
        getPartsList(part.getType()).add(part);
        MAPPED_PARTS.put(part.getPartName(), part);
    }
    public static WeaponPart get(String name) {
        return MAPPED_PARTS.get(name);
    }

    public static List<WeaponPartBase> getBaseParts() {
        return WEAPON_PARTS;
    }
    public static void addBasePart(WeaponPartBase part) {
        WEAPON_PARTS.add(part);
    }
    public static void generateWeaponParts() {
        List<WeaponPartBase> baseWeaponParts = WeaponParts.getBaseParts();
        for (WeaponPartBase baseWeaponPart : baseWeaponParts) {
            List<MaterialType> materialTypes = baseWeaponPart.getMaterials();
            for (MaterialType materialType : materialTypes) {
                List<Material> materials = Materials.getMaterials(materialType);
                for (Material material : materials) {
                    register(new WeaponPartBasic(baseWeaponPart, material));
                }
            }
        }
    }

    public static List<WeaponPart> getPartsList(WeaponPartType type) {
        if (!PARTS_LISTS.containsKey(type)) PARTS_LISTS.put(type, new ArrayList<>());
        return PARTS_LISTS.get(type);
    }
    public static void countParts() {
        for(WeaponPartType type : PARTS_LISTS.keySet()) {
            List<WeaponPart> parts = getPartsList(type);
            System.out.println(parts.size() + " " + type.getTypeName());
        }
        DecimalFormat format = new DecimalFormat("#,###");
        long total = 0;
        for(WeaponType weaponType : WeaponTypes.getWeaponTypes()) {
            long sub = 1;
            for(WeaponPartType type : weaponType.getWeaponPartTypes()) {
                List<WeaponPart> parts = getPartsList(type);
                if(parts != null && !parts.isEmpty()) sub *= parts.size();
            }
            total += sub;
            System.out.println(format.format(sub) + " total " + weaponType.getWeaponTypeName() + "s");
        }
        System.out.println(format.format(total) + " total weapons available!");
    }
    public static void enumerateParts(boolean storeFile) {
        TimeKeeper timer = new TimeKeeper();
        timer.start("Enumeration all weapon parts. . .");

        List<WeaponPart> parts = getAll();
        parts.sort(Comparator.comparing(WeaponPart::getPartName));

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
        timer.stop();
    }
    public static List<WeaponPart> getAll() {
        List<WeaponPart> ret = new ArrayList<>();
        PARTS_LISTS.values().forEach(ret::addAll);
        return ret;
    }
}