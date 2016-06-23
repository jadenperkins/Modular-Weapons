package com.jadencode.main.generate.weapon;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.QualityObject;
import com.jadencode.main.stat.StatSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 6/1/2015.
 */
public class WeaponInstance implements QualityObject {

    private final WeaponType weaponType;
    private final List<WeaponPartInstance> weaponParts;
    private final HashMap<WeaponPartType, WeaponPartInstance> mappedParts;
    private final int level;
    private final String          displayName;
    private final List<String> displayInfo;
    private final StatSet statSet;

    public WeaponInstance(WeaponType base, List<WeaponPartInstance> parts) {
        this.weaponType = base;
        this.weaponParts = parts;
        this.level = Collections.max(parts.stream().map(WeaponPartInstance::getLevel).collect(Collectors.toList()));
        this.mappedParts = new HashMap<>();
        parts.forEach(part -> this.mappedParts.put(part.getWeaponPart().getType(), part));
        this.displayName = base.getDisplayName(this);

        this.displayInfo = parts.stream().map(WeaponPartInstance::getPartInfo).collect(Collectors.toList());

        this.statSet = this.weaponType.determineStats(this);
    }
    public WeaponInstance scaled(int i) {
        List<WeaponPartInstance> parts = new ArrayList<>();
        this.getPartsList().forEach(p -> parts.add(p.scaledInstance(i)));
        return new WeaponInstance(this.getWeaponType(), parts);
    }
    public List<WeaponPartInstance> getPartsList() {
        return this.weaponParts;
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
        return this.mappedParts.get(type);
    }
    public StatSet getStatSet() {
        return this.statSet;
    }
    public WeaponType getWeaponType() {
        return this.weaponType;
    }
    public QualityLevel getQualityLevel() {
        return QualityLevel.calculate(this);
    }
    @Override
    public List<QualityLevel> getQualityLevels() {
        List<QualityLevel> ret = this.weaponParts.stream().map(WeaponPartInstance::getQualityLevel).collect(Collectors.toList());
        return ret;
    }
}