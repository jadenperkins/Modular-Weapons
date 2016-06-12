package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponParts;
import com.jadencode.main.stat.StatSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 6/1/2015.
 */
public class WeaponInstance {

    private WeaponType weaponType;
    private final HashMap<WeaponPartType, WeaponPartInstance> weaponParts;
    private final int level;
    private final String          displayName;
    private final List<String> displayInfo = new ArrayList<>();
    private final List<String> weaponInfo  = new ArrayList<>();
    private final StatSet statSet;

    public WeaponInstance(WeaponType base, HashMap<WeaponPartType, WeaponPartInstance> parts) {
        this.weaponType = base;
        this.level = Collections.max(parts.values().stream().map(WeaponPartInstance::getLevel).collect(Collectors.toList()));
        this.weaponParts = parts;
//        this.displayName = base.getWeaponClassName();
        this.displayName = parts.get(WeaponParts.PART_SWORD_HILT).getWeaponPart().getBaseWeaponPart().getNameMod() + " " +
                parts.get(WeaponParts.PART_SWORD_GRIP).getWeaponPart().getBaseWeaponPart().getNameMod() + " " +
                parts.get(WeaponParts.PART_SWORD_BLADE).getWeaponPart().getBaseWeaponPart().getNameMod();
        this.weaponParts.values().forEach(s -> this.weaponInfo.add(s.getPartInfo()));
        this.displayInfo.addAll(this.getWeaponInfo());

        this.statSet = this.weaponType.determineStats(this);
    }
    public WeaponInstance scaled(int i) {
        HashMap<WeaponPartType, WeaponPartInstance> parts = new HashMap<>();
        this.getWeaponParts().forEach((k, v) -> parts.put(k, v.scaledInstance(i)));
        return new WeaponInstance(this.getWeaponType(), parts);
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
    public WeaponPartInstance getPart(WeaponPartType type) {
        return this.weaponParts.get(type);
    }

    public List<String> getWeaponInfo() {
        return this.weaponInfo;
    }

    public HashMap<WeaponPartType, WeaponPartInstance> getWeaponParts() {
        return this.weaponParts;
    }
    public StatSet getStatSet() {
        return this.statSet;
    }
    public WeaponType getWeaponType() {
        return this.weaponType;
    }
}