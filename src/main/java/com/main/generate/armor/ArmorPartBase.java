package com.main.generate.armor;

import com.main.material.MaterialModified;
import com.main.material.MaterialType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class ArmorPartBase {

    private static final List<ArmorPartBase> ARMOR_PARTS = new ArrayList<>();

//    public static final ArmorPartBase plateHelmetBase = new ArmorPartBase("Plate Sallet", "Plate Helmet", 90F, 80F, 30F, 30F, ArmorGenerator.MELEE_HELMET_BASES, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase fluteHelmetBase = new ArmorPartBase("Fluted Sallet", "Fluted Helmet", 45F, 70F, 80F, 25F, ArmorGenerator.MELEE_HELMET_BASES, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase chainHelmetBase = new ArmorPartBase("Chainmail Coif", "Chain Coif", 70F, 90F, 30F, 20F, ArmorGenerator.MELEE_HELMET_BASES, MaterialType.getMetalLibrary());
//
//    public static final ArmorPartBase plateHelmetAddon = new ArmorPartBase("Plate Visor", "Plated", 10F, 6F, -2F, 3F, ArmorGenerator.MELEE_HELMET_ADDS, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase fluteHelmetAddon = new ArmorPartBase("Fluted Mask", "Fluted", -3F, 5F, 9F, 2F, ArmorGenerator.MELEE_HELMET_ADDS, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase chainHelmetAddon = new ArmorPartBase("Chainmail Necking", "Chain-necked", 5F, 10F, -2F, 1F, ArmorGenerator.MELEE_HELMET_ADDS, MaterialType.getMetalLibrary());

    private final String          partName;
    private final List<ArmorPart> partList;
    private final String          nameMod;
    private final List<MaterialModified> materials = new ArrayList<>();
    private final boolean usesMaterials;
    private final float   weight;
    private final float   slash;
    private final float   pierce;
    private final float   blunt;
    private final float   mobility;

    public ArmorPartBase(String name, String mod, List<ArmorPart> list, MaterialType... mats) {
        this(name, mod, 1F, 1F, 1F, 1F, list, mats);
    }

    public ArmorPartBase(String name, String mod, float sls, float prc, float bnt, float mob, List<ArmorPart> list, MaterialType... mats) {
        this(name, mod, 1F, sls, prc, bnt, mob, list, mats);
    }

    public ArmorPartBase(String name, String mod, float weight, float sls, float prc, float bnt, float mob, List<ArmorPart> list, MaterialType... mats) {
        this.partName = name;
        this.nameMod = mod;
        this.weight = weight;
        this.partList = list;

        this.slash = sls;
        this.pierce = prc;
        this.blunt = bnt;
        this.mobility = mob;

        for (MaterialType lib : mats) {
//            this.materials.addAll(lib.getMaterialResources().values());
        }
        this.usesMaterials = !this.materials.isEmpty();

        ARMOR_PARTS.add(this);
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

    public List<ArmorPart> getPartList() {
        return partList;
    }

    public String getNameMod() {
        return nameMod;
    }

    public List<MaterialModified> getMaterials() {
        return materials;
    }

    public float getSlash() {
        return slash;
    }

    public float getBlunt() {
        return blunt;
    }

    public float getPierce() {
        return pierce;
    }

    public float getMobility() {
        return mobility;
    }

    public static List<ArmorPartBase> getBaseParts() {
        return ARMOR_PARTS;
    }

//    public static void enumerateParts(boolean storeFile) {
//        TimeKeeper timer = new TimeKeeper();
//        timer.start("Enumeration all weapon parts. . .");
//
//        Comparator<ArmorPart> comparator = (a, b) -> a.getPartName().compareTo(b.getPartName());
//        List<ArmorPart> parts = ArmorGenerator.getAll();
//        parts.sort(comparator);
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
//        for(ArmorPart part : parts) {
//            String msg = String.format("%s, %s\n\t", part.getPartName(), part.getPartInfo());
//            if(part.hasBaseResource()) {
//                msg += part.getBaseResource().getName();
//            } else {
//                msg += "No Resource";
//            }
//            writer.println(msg);
//        }
//        writer.close();
//        timer.stopAndDisplay();
//    }
}