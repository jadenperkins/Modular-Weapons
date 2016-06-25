package com.jadencode.main.generate.item.base;

import com.jadencode.main.generate.item.instance.ItemMaterialized;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.stat.StatSet;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemMaterializedBase {
    private final String             partName;
    private final String             description;
    private final List<MaterialType> materials;
    private final float              weight;
    private final StatSet            statSet;
    private final BufferedImage      icon;

    public ItemMaterializedBase(String name, String info, float weight, StatSet stats, BufferedImage icon, List<MaterialType> mats) {
        this.partName = name;
        this.description = info;
        this.weight = weight;

        this.statSet = stats;
        this.icon = icon;
        this.materials = mats;
    }
    public String getPartName() {
        return partName;
    }
    public String getDescription() {
        return description;
    }
    public List<MaterialType> getMaterials() {
        return materials;
    }
    public float getWeight() {
        return weight;
    }
    public StatSet getStatSet() {
        return statSet;
    }
    public BufferedImage getIcon() {
        return this.icon;
    }
    public StatSet modifyStats(Material resource) {
        return this.getStatSet().modified(resource);
    }
}
