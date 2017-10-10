package com.main.item;


import com.main.TimeKeeper;
import com.main.material.MaterialModified;
import com.main.material.MaterialType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jaden on 6/16/2015.
 */
public class ItemPartBase {

    private static final List<ItemPartBase> ITEM_PARTS = new ArrayList<>();

//    public static final ItemPartBase ingot = new ItemPartBase("Ingot", "Ingot", ItemPart.INGOTS, MaterialType.getMetalLibrary());
//    public static final ItemPartBase jewel = new ItemPartBase("Jewel", "Jewel", ItemPart.JEWELS, MaterialType.getGemLibrary());
//    public static final ItemPartBase ore   = new ItemPartBase("Ore", "Ore", ItemPart.ORES, MaterialType.getMetalLibrary());
//    public static final ItemPartBase log   = new ItemPartBase("Log", "Log", ItemPart.LOGS, MaterialType.getWoodLibrary());
//    public static final ItemPartBase fiber = new ItemPartBase("Plant Fiber", "Plant Fiber", ItemPart.FIBERS, MaterialType.getPlantLibrary());
//    public static final ItemPartBase coin  = new ItemPartBase("Coin", "Coin", ItemPart.MISC);

    private final String         partName;
    private final List<ItemPart> partList;
    private final String         nameMod;
    private final List<MaterialModified> materials = new ArrayList<>();
    private final boolean usesMaterials;
    private final float   weight;

    public ItemPartBase(String name, String mod, List<ItemPart> list, MaterialType... mats) {
        this(name, mod, 1F, list, mats);
    }

    public ItemPartBase(String name, String mod, float weight, List<ItemPart> list, MaterialType... mats) {
        this.partName = name;
        this.nameMod = mod;
        this.weight = weight;
        this.partList = list;
        for (MaterialType lib : mats) {
//            this.materials.addAll(lib.getMaterialResources().values());
        }
        this.usesMaterials = !this.materials.isEmpty();
        ITEM_PARTS.add(this);
    }
    public boolean usesMaterials() {
        return usesMaterials;
    }

    public float getWeight() {
        return weight;
    }

    public String getPartName() {
        return partName;
    }

    public List<ItemPart> getPartList() {
        return partList;
    }

    public String getNameMod() {
        return nameMod;
    }

    public List<MaterialModified> getMaterials() {
        return materials;
    }

    public static List<ItemPartBase> getBaseParts() {
        return ITEM_PARTS;
    }

    public static void enumerateParts(boolean storeFile) {
        TimeKeeper timer = new TimeKeeper();
        timer.start("Enumeration all weapon parts. . .");

        Comparator<ItemPart> comparator = (a, b) -> a.getPartName().compareTo(b.getPartName());
        List<ItemPart> parts = ItemPart.getAll();//WeaponGenerator.getAll();
        parts.sort(comparator);

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
        for(ItemPart part : parts) {
            String msg = String.format("%s, %s\n\t", part.getPartName(), part.getPartInfo());
            if(part.hasBaseResource()) {
                msg += part.getBaseResource().getName();
            } else {
                msg += "No Resource";
            }
            writer.println(msg);
        }
        writer.close();
        timer.stop();
    }
}
