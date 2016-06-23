package com.jadencode.main.generate.weapon;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.QualityObject;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public abstract class WeaponPart implements WeightedItem, QualityObject {
    private final float weight;
    private final WeaponPartType type;
    private final BufferedImage icon;

    public WeaponPart(float w, BufferedImage icon, WeaponPartType type) {
        this.weight = w;
        this.icon = icon;
        this.type = type;
    }
    public WeaponPartType getType() {
        return type;
    }
    public BufferedImage getIcon() {
        return this.icon == null ? this.getType().getIcon() : this.icon;
    }
    @Override
    public float getWeight() {
        return this.weight;
    }
    public abstract String getPartName();
    public abstract String getNameMod();
    public abstract String getPartInfo();
    public abstract StatSet getStats();
    public abstract Color getColor();
}