package com.jadencode.main.generate.item;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public abstract class WeaponPart implements WeightedItem {
    private final float weight;
    private final WeaponPartType type;
    private final BufferedImage icon;
    private final QualityLevel qualityLevel;

    public WeaponPart(float w, BufferedImage icon, QualityLevel level, WeaponPartType type) {
        this.weight = w;
        this.icon = icon;
        this.qualityLevel = level;
        this.type = type;
    }
    public WeaponPartType getType() {
        return type;
    }
    public BufferedImage getIcon() {
        return this.icon == null ? this.getType().getIcon() : this.icon;
    }
    public QualityLevel getQualityLevel() {
        return this.qualityLevel;
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
    public abstract String getMaterialName();
}