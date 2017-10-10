package com.main.generate.armor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jaden on 6/1/2015.
 */
public class ArmorInstance {

    private       ArmorClass                         armorClass;
    private final HashMap<String, ArmorPartInstance> armorParts;
    private int level = 1;
    private final String displayName;
    private final List<String> displayInfo = new ArrayList<>();
    private final List<String> armorInfo   = new ArrayList<>();
    private final float slashValue;
    private final float pierceValue;
    private final float bluntValue;
    private final float mobilityValue;

    public ArmorInstance(ArmorClass base, int level, HashMap<String, ArmorPartInstance> parts) {
        this.armorClass = base;
        this.level = level;
        this.armorParts = parts;
        this.displayName = base.getArmorClassName();
        this.armorParts.values().forEach(s -> this.armorInfo.add(s.getPartInfo()));
        this.displayInfo.addAll(this.getArmorInfo());

        float mult = (float) Math.pow(1.1F, this.level - 1);

        float sls = this.armorClass.getBaseSlash() * mult;
        float prc = this.armorClass.getBasePierce() * mult;
        float bnt = this.armorClass.getBaseBlunt() * mult;
        float mob = this.armorClass.getBaseMobility();

        for(ArmorPartInstance part : this.getArmorParts().values()) {
//            sls += part.getSlashMod();
//            prc += part.getPierceMod();
//            bnt += part.getBluntMod();
//            mob += part.getMobilityMod();
        }
        this.slashValue = sls;
        this.pierceValue = prc;
        this.bluntValue = bnt;
        this.mobilityValue = mob;
    }

    public float getSlashValue() {
        return slashValue;
    }

    public float getPierceValue() {
        return pierceValue;
    }

    public float getBluntValue() {
        return bluntValue;
    }

    public float getMobilityValue() {
        return mobilityValue;
    }

    public int getLevel() {
        return this.level;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<String> getDisplayInfo() {
        return this.displayInfo;
    }
    public void addPart(String type, ArmorPartInstance part) {
        this.armorParts.put(type, part);
    }
    public ArmorPartInstance getPart(String type) {
        return this.armorParts.get(type);
    }

    public List<String> getArmorInfo() {
        return this.armorInfo;
    }

    public HashMap<String, ArmorPartInstance> getArmorParts() {
        return this.armorParts;
    }
}