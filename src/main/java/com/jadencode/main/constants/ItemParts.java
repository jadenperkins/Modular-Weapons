package com.jadencode.main.constants;

import com.jadencode.main.TimeKeeper;
import com.jadencode.main.generate.item.base.ItemPartBase;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.type.ItemType;
import com.jadencode.main.generate.item.type.ItemTypePart;
import com.jadencode.main.generate.item.type.ItemTypePartMaterialized;
import com.jadencode.main.material.Material;
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
public final class ItemParts {
    private static final List<ItemPartBase> ITEM_PARTS = new ArrayList<>();
    private static final HashMap<ItemPartType, List<ItemTypePart>> PARTS_LISTS = new HashMap<>();
    private static final HashMap<String, ItemTypePart> MAPPED_PARTS = new HashMap<>();

    public static void register(ItemTypePart part) {
        ItemTypes.register(part);
        getPartsList(part.getType()).add(part);
        MAPPED_PARTS.put(part.getItemBaseName(), part);
    }
    public static ItemTypePart get(String name) {
        return MAPPED_PARTS.get(name);
    }

    public static List<ItemPartBase> getBaseParts() {
        return ITEM_PARTS;
    }
    public static void addBasePart(ItemPartBase part) {
        ITEM_PARTS.add(part);
    }
    public static void generateItemParts() {
        ItemParts.getBaseParts()
                .forEach(basePart -> basePart.getMaterials()
                        .forEach(type -> Materials.getMaterials(type)
                                .forEach(material -> register(new ItemTypePartMaterialized(basePart, material)))));
    }

    public static List<ItemTypePart> getPartsList(ItemPartType type) {
        if(PARTS_LISTS.containsKey(type)) return PARTS_LISTS.get(type);
        List<ItemTypePart> parts = new ArrayList<>();
        PARTS_LISTS.put(type, parts);
        return parts;
    }
//    public static void countParts() {
//        for(ItemPartType type : PARTS_LISTS.keySet()) {
//            List<ItemTypePart> parts = getPartsList(type);
//            System.out.println(parts.size() + " " + type.getTypeName());
//        }
//        DecimalFormat format = new DecimalFormat("#,###");
//        long total = 0;
//        for(ItemType itemType : ItemTypes.getItemTypes()) {
//            long sub = 1;
////            for(ItemPartType type : itemType.get) {
////                List<WeaponPart> parts = getPartsList(type);
////                if(parts != null && !parts.isEmpty()) {
////                    sub *= parts.size();
////                }
////            }
//            total += sub;
//            System.out.println(format.format(sub) + " total " + itemType.getWeaponTypeName() + "s");
//        }
//        System.out.println(format.format(total) + " total weapons available!");
//    }
//    public static void enumerateParts(boolean storeFile) {
//        TimeKeeper timer = new TimeKeeper();
//        timer.start("Enumeration all item parts. . .");
//
//        List<WeaponPart> parts = getAll();
//        parts.sort((a, b) -> a.getPartName().compareTo(b.getPartName()));
//
//        PrintStream writer = System.out;
//
//        if(storeFile) {
//            File out = new File("./enum.txt");
//
//            try {
//                out.createNewFile();
//                writer = new PrintStream(new FileOutputStream(out));
//
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//        for(WeaponPart part : parts) {
//            String msg = String.format("%s, %s\n\t", part.getPartName(), part.getPartInfo());
//            writer.println(msg);
//        }
//        writer.close();
//        timer.stopAndDisplay();
//    }
//    public static List<WeaponPart> getAll() {
//        List<WeaponPart> ret = new ArrayList<>();
//        PARTS_LISTS.values().forEach(ret::addAll);
//        return ret;
//    }
}