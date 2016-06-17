package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponParts;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public abstract class WeaponPart implements WeightedItem {
    private final float weight;
    private final WeaponPartType type;

    public WeaponPart(float w, WeaponPartType type) {
        this.weight = w;
        this.type = type;
    }
    public WeaponPartType getType() {
        return type;
    }
    @Override
    public float getWeight() {
        return this.weight;
    }
    public abstract String getPartName();
    public abstract String getNameMod();
    public abstract String getPartInfo();
    public abstract StatSet getStats();
}