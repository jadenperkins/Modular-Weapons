package com.main.item;

import com.main.BiMap;
import com.main.material.MaterialModified;
import com.main.util.WeightedItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jaden on 6/16/2015.
 */
public class ItemPart implements WeightedItem {

    public static final String KEY_INGOTS = "ingots";
    public static final String KEY_JEWELS = "jewels";
    public static final String KEY_LOGS   = "logs";
    public static final String KEY_ORES   = "ores";
    public static final String KEY_FIBERS = "fibers";
    public static final String KEY_MISC   = "misc";

    private static final HashMap<String, List<ItemPart>>                    itemPartsLists = new HashMap<>();
    private static final HashMap<ItemPartBase, List<ItemPart>>              mappedParts    = new HashMap<>();
    private static final HashMap<MaterialModified, List<ItemPart>>          resourcedParts = new HashMap<>();
    private static final BiMap<ItemPartBase, MaterialModified, ItemPart> stackedParts   = new BiMap<>();

    public static final List<ItemPart> MISC   = getItemParts(KEY_MISC);
    public static final List<ItemPart> INGOTS = getItemParts(KEY_INGOTS);
    public static final List<ItemPart> JEWELS = getItemParts(KEY_JEWELS);
    public static final List<ItemPart> LOGS   = getItemParts(KEY_LOGS);
    public static final List<ItemPart> ORES   = getItemParts(KEY_ORES);
    public static final List<ItemPart> FIBERS = getItemParts(KEY_FIBERS);

    private final ItemPartBase     baseItemPart;
    private final MaterialModified baseResource;
    private final String           partName;
    private final String           partDescription;
    private final float            weight;

    public ItemPart(ItemPartBase part, MaterialModified resource) {
        this.baseItemPart = part;
        this.baseResource = resource;
        this.partName = resource.getName() + " " + part.getPartName();
        this.partDescription = String.format("A %s crafted consume %s", part.getPartName(), resource.getName());
        this.weight = part.getWeight() * resource.getWeight();
        part.getPartList().add(this);
        mapPart();
    }
    public ItemPart(ItemPartBase part) {//}, StatSet stats) {
        this.baseItemPart = part;
        this.baseResource = null;
        this.partName = part.getPartName();
        this.partDescription = String.format("A legendary %s", part.getPartName());
        this.weight = part.getWeight() * 1F;
        part.getPartList().add(this);
    }
    private void mapPart() {
        List<ItemPart> parts = mappedParts.get(this.getBaseItemPart());
        if(parts == null) {
            parts = new ArrayList<>();
            mappedParts.put(this.getBaseItemPart(), parts);
        }
        parts.add(this);

        List<ItemPart> parts1 = resourcedParts.get(this.getBaseResource());
        if(parts1 == null) {
            parts1 = new ArrayList<>();
            resourcedParts.put(this.getBaseResource(), parts1);
        }
        parts1.add(this);

        stackedParts.put(this.getBaseItemPart(), this.getBaseResource(), this);
    }
    /**Get all ItemPart instances that use base as the ItemPartBase*/
    public static List<ItemPart> getItemParts(ItemPartBase base) {
        List<ItemPart> parts = mappedParts.get(base);
        return parts;
    }
    /**Get all ItemPart instances that use res as the MaterialModified*/
    public static List<ItemPart> getItemParts(MaterialModified res) {
        List<ItemPart> parts = resourcedParts.get(res);
        return parts;
    }
    /**Get an ItemPart instance that uses base as the ItemPartBase and res as the MaterialModified. If res is null,
     * it returns the only ItemPart instance that uses ItemPartBase as the base*/
    public static ItemPart getItemPart(ItemPartBase base, MaterialModified res) {
        if(res == null) {
            System.out.println(String.format("Base item is %s, resource is %s", base.getPartName(), res.getName()));
            return getItemParts(base).get(0);
        }
        return stackedParts.get(base, res);
    }

    public static List<ItemPart> getItemParts(String key) {
        List<ItemPart> parts = itemPartsLists.get(key);
        if (parts == null) {
            parts = new ArrayList<>();
            itemPartsLists.put(key, parts);
        }
        return parts;
    }

    public ItemPartBase getBaseItemPart() {
        return this.baseItemPart;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    public boolean hasBaseResource() {
        return this.getBaseResource() != null;
    }
    public MaterialModified getBaseResource() {
        return this.baseResource;
    }

    public String getPartName() {
        return this.partName;
    }
    public String getPartInfo() {
        return this.partDescription;
    }

    public static void generateItemParts() {
        for(ItemPartBase partBase : ItemPartBase.getBaseParts()) {
            if(partBase.usesMaterials()) {
                List<MaterialModified> resourceList = partBase.getMaterials();
                for(MaterialModified resource : resourceList) {
//                    StatSet stats = baseStats.copyOf(resource.getStatModifier(), resource.getStatShift());
                    ItemPart part = new ItemPart(partBase, resource);//, stats);
                }
            } else {
//                StatSet stats = baseStats;
                ItemPart part = new ItemPart(partBase);//, stats);
            }
        }
    }

    public static List<ItemPart> getAll() {
        List<ItemPart> ret = new ArrayList<>();
        itemPartsLists.values().forEach(i -> ret.addAll(i));
        return ret;
    }
}
