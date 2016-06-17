package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.StatSets;
import com.jadencode.main.stat.StatSet;

import java.awt.*;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public class WeaponPartLegendary extends WeaponPart {
    private final String partName;
    private final String nameMod;
    private final String partInfo;
    private final StatSet statSet;

    public WeaponPartLegendary(String partName, String nameMod, float weight, WeaponPartType type) {
        this(partName, nameMod, "A legendary part", StatSets.EMPTY, weight, type);
    }
    public WeaponPartLegendary(String partName, String nameMod, String partInfo, StatSet statSet, float weight, WeaponPartType type) {
        super(weight, type);
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
        return Color.WHITE;
    }
}