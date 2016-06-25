package com.jadencode.main.generate.item;

import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.stat.StatSet;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponPartBase {
//    public static final ArmorPartBase plateHelmetBase = new ArmorPartBase("Plate Sallet", "Plate Helmet", 90F, 80F, 30F, 30F, ArmorGenerator.MELEE_HELMET_BASES, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase fluteHelmetBase = new ArmorPartBase("Fluted Sallet", "Fluted Helmet", 45F, 70F, 80F, 25F, ArmorGenerator.MELEE_HELMET_BASES, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase chainHelmetBase = new ArmorPartBase("Chainmail Coif", "Chain Coif", 70F, 90F, 30F, 20F, ArmorGenerator.MELEE_HELMET_BASES, MaterialType.getMetalLibrary());
//
//    public static final ArmorPartBase plateHelmetAddon = new ArmorPartBase("Plate Visor", "Plated", 10F, 6F, -2F, 3F, ArmorGenerator.MELEE_HELMET_ADDS, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase fluteHelmetAddon = new ArmorPartBase("Fluted Mask", "Fluted", -3F, 5F, 9F, 2F, ArmorGenerator.MELEE_HELMET_ADDS, MaterialType.getMetalLibrary());
//    public static final ArmorPartBase chainHelmetAddon = new ArmorPartBase("Chainmail Necking", "Chain-necked", 5F, 10F, -2F, 1F, ArmorGenerator.MELEE_HELMET_ADDS, MaterialType.getMetalLibrary());
//
//    public static final ItemPartBase ingot = new ItemPartBase("Ingot", "Ingot", ItemPart.INGOTS, MaterialType.getMetalLibrary());
//    public static final ItemPartBase jewel = new ItemPartBase("Jewel", "Jewel", ItemPart.JEWELS, MaterialType.getGemLibrary());
//    public static final ItemPartBase ore   = new ItemPartBase("Ore", "Ore", ItemPart.ORES, MaterialType.getMetalLibrary());
//    public static final ItemPartBase log   = new ItemPartBase("Log", "Log", ItemPart.LOGS, MaterialType.getWoodLibrary());
//    public static final ItemPartBase fiber = new ItemPartBase("Plant Fiber", "Plant Fiber", ItemPart.FIBERS, MaterialType.getPlantLibrary());
//    public static final ItemPartBase coin  = new ItemPartBase("Coin", "Coin", ItemPart.MISC);



    private final WeaponPartType partType;
    private final String             partName;
    private final String             nameMod;
    private final List<MaterialType> materials;
    private final float              weight;
    private final StatSet            statSet;
    private final BufferedImage      icon;

    public WeaponPartBase(String name, String mod, float weight, StatSet stats, WeaponPartType type, BufferedImage icon, List<MaterialType> mats) {
        this.partName = name;
        this.nameMod = mod;
        this.weight = weight;
        this.partType = type;

        this.statSet = stats;
        this.icon = icon;
        this.materials = mats;
    }
    public BufferedImage getIcon() {
        return this.icon;
    }
    public float getWeight() {
        return weight;
    }

    public String getPartName() {
        return partName;
    }

    public WeaponPartType getPartType() {
        return partType;
    }

    public String getNameMod() {
        return nameMod;
    }

    public List<MaterialType> getMaterials() {
        return materials;
    }

    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet modifyStats(Material resource) {
        return this.getStatSet().modified(resource);
    }
    public StatSet scaleStats(WeaponPartInstance partInstance) {
        return this.statSet.scaled(partInstance.getLevel());
    }
}