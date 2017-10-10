package com.main.generate.weapon;

import com.main.generate.QualityLevel;
import com.main.stat.StatSet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public class WeaponPartLegendary extends WeaponPart {
    private final String partName;
    private final String nameMod;
    private final String partInfo;
    private final StatSet statSet;

    public WeaponPartLegendary(String partName, String nameMod, String partInfo, StatSet statSet, float weight, BufferedImage icon, WeaponPartType type) {
        super(weight, icon, QualityLevel.LEGENDARY, type);
        this.partName = partName;
        this.nameMod = nameMod;
        this.partInfo = partInfo;
        this.statSet = statSet;
    }
    @Override
    public String getPartName() {
        return partName;
    }
    @Override
    public String getNameMod() {
        return nameMod;
    }
    @Override
    public String getPartInfo() {
        return partInfo;
    }
    @Override
    public StatSet getStats() {
        return statSet;
    }
    @Override
    public Color getColor() {
        return null;
    }
    @Override
    public String getMaterialName() {
        return "";
    }
}