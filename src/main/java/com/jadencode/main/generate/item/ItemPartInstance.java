package com.jadencode.main.generate.item;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.stat.StatSet;

import java.awt.*;

/**
 * Created by Jaden on 6/15/2015.
 */
public class ItemPartInstance implements Comparable<ItemPartInstance> {
    private final ItemPart weaponPart;
    private final int        level;
    private final StatSet    statSet;

    public ItemPartInstance(ItemPart part, int l) {
        this.weaponPart = part;
        this.level = l;

        this.statSet = this.getWeaponPart().getStats().scaled(this.level);
    }
    public ItemPartInstance scaledInstance(int i) {
        ItemPartInstance scaled = new ItemPartInstance(this.weaponPart, i);
        return scaled;
    }
    public int getLevel() {
        return level;
    }
    public Color getColor() {
        return this.getWeaponPart().getColor();
    }
    public ItemPart getWeaponPart() {
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
    public int compareTo(ItemPartInstance o) {
        return Integer.compare(this.level, o.level);
    }
}