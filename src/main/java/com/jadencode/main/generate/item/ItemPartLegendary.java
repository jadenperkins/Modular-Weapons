package com.jadencode.main.generate.item;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.stat.StatSet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public class ItemPartLegendary extends ItemPart {
    private final String partName;
    private final String nameMod;
    private final String partInfo;
    private final StatSet statSet;

    public ItemPartLegendary(String partName, String nameMod, String partInfo, StatSet statSet, float weight, BufferedImage icon, ItemPartType type) {
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