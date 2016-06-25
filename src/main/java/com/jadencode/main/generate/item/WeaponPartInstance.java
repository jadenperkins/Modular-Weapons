package com.jadencode.main.generate.item;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.stat.StatSet;

import java.awt.*;

/**
 * Created by Jaden on 6/15/2015.
 */
public class WeaponPartInstance implements Comparable<WeaponPartInstance> {
    private final WeaponPart weaponPart;
    private final int        level;
    private final StatSet    statSet;

    public WeaponPartInstance(WeaponPart part, int l) {
        this.weaponPart = part;
        this.level = l;

        this.statSet = this.getWeaponPart().getStats().scaled(this.level);
    }
    public WeaponPartInstance scaledInstance(int i) {
        WeaponPartInstance scaled = new WeaponPartInstance(this.weaponPart, i);
        return scaled;
    }
    public int getLevel() {
        return level;
    }
    public Color getColor() {
        return this.getWeaponPart().getColor();
    }
    public WeaponPart getWeaponPart() {
        return weaponPart;
    }
    public StatSet getStats() {
        return this.statSet;
    }
    public String getPartInfo() {
        return String.format("%s (Level %d)", this.getWeaponPart().getPartInfo(), this.getLevel());
    }
    public String getNameMod() {
        return this.weaponPart.getNameMod();
    }
    public QualityLevel getQualityLevel() {
        return this.weaponPart.getQualityLevel();
    }
    @Override
    public int compareTo(WeaponPartInstance o) {
        return Integer.compare(this.level, o.level);
    }
}