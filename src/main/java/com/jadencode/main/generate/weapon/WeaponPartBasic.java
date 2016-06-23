package com.jadencode.main.generate.weapon;


import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.QualityObject;
import com.jadencode.main.material.Material;
import com.jadencode.main.stat.StatSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponPartBasic extends WeaponPart {

    private final String           partName;
    private final String           nameMod;
    private final String           partDescription;
    private final StatSet          stats;
    private final Color            partColor;
    private final QualityLevel     qualityLevel;

    public WeaponPartBasic(WeaponPartBase part, Material resource) {
        super(part.getWeight() * resource.getWeight(), part.getIcon(), part.getPartType());
        this.qualityLevel = resource.getQualityLevel();
        this.partName = resource.getName() + " " + part.getPartName();
        this.nameMod = part.getNameMod();
        this.partDescription = String.format("A %s crafted from %s", part.getPartName(), resource.getName());
        this.stats = part.modifyStats(resource);
        this.partColor = resource.getColor();
    }
    @Override
    public String getPartName() {
        return this.partName;
    }
    @Override
    public String getNameMod() {
        return this.nameMod;
    }
    @Override
    public String getPartInfo() {
        return this.partDescription;
    }
    @Override
    public StatSet getStats() {
        return this.stats;
    }
    @Override
    public Color getColor() {
        return this.partColor;
    }
    @Override
    public QualityLevel getQualityLevel() {
        return this.qualityLevel;
    }
    @Override
    public List<QualityObject> getQualityObjects() {
        return Arrays.asList(this);
    }
}
