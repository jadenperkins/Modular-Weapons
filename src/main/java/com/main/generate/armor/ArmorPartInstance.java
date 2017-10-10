package com.main.generate.armor;

/**
 * Created by Jaden on 6/15/2015.
 */
public class ArmorPartInstance {
    private final ArmorPart armorPart;
    private final int       level;
//    private final float     slashMod;
//    private final float     pierceMod;
//    private final float     bluntMod;
//    private final float     mobilityMod;

    public ArmorPartInstance(ArmorPart part, int l) {
        this.armorPart = part;
        this.level = l;
        float mult = (float) Math.pow(1.1F, l - 1);

//        this.slashMod = part.getSlash() * mult;
//        this.pierceMod = part.getPierce() * mult;
//        this.bluntMod = part.getBlunt() * mult;
//        this.mobilityMod = part.getMobility();
    }

    public int getLevel() {
        return level;
    }

    public ArmorPart getArmorPart() {
        return armorPart;
    }
    public String getPartInfo() {
        return "Empty Info";
//        return String.format("%s (Level %d)", this.getArmorPart().getPartInfo(), this.getLevel());
    }

//    public float getSlashMod() {
//        return slashMod;
//    }
//
//    public float getPierceMod() {
//        return pierceMod;
//    }
//
//    public float getBluntMod() {
//        return bluntMod;
//    }
//
//    public float getMobilityMod() {
//        return mobilityMod;
//    }
}