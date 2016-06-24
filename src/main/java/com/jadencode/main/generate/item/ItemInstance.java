package com.jadencode.main.generate.item;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.QualityObject;
import com.jadencode.main.stat.StatSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 6/1/2015.
 */
public class ItemInstance implements QualityObject {

    private final ItemType weaponType;
    private final List<ItemPartInstance> weaponParts;
    private final HashMap<ItemPartType, ItemPartInstance> mappedParts;
    private final int level;
    private final String          displayName;
    private final List<String> displayInfo;
    private final StatSet statSet;

    public ItemInstance(ItemType base, List<ItemPartInstance> parts) {
        this.weaponType = base;
        this.weaponParts = parts;
        this.level = (int) Math.ceil(Math.sqrt(parts.stream().mapToInt(part -> (int)Math.pow(part.getLevel(), 2)).sum() / parts.size()));
//        this.level = Collections.max(parts.stream().map(WeaponPartInstance::getLevel).collect(Collectors.toList()));
        this.mappedParts = new HashMap<>();
        parts.forEach(part -> this.mappedParts.put(part.getWeaponPart().getType(), part));
        this.displayName = base.getDisplayName(this);

        this.displayInfo = parts.stream().map(ItemPartInstance::getPartInfo).collect(Collectors.toList());

        this.statSet = this.weaponType.determineStats(this);
    }
    public ItemInstance scaled(int i) {
        List<ItemPartInstance> parts = new ArrayList<>();
        this.getPartsList().forEach(p -> parts.add(p.scaledInstance(i)));
        return new ItemInstance(this.getWeaponType(), parts);
    }
    public List<ItemPartInstance> getPartsList() {
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
    public ItemPartInstance getPart(ItemPartType type) {
        return this.mappedParts.get(type);
    }
    public StatSet getStatSet() {
        return this.statSet;
    }
    public ItemType getWeaponType() {
        return this.weaponType;
    }
    public QualityLevel getQualityLevel() {
        return QualityLevel.calculate(this);
    }
    @Override
    public List<QualityLevel> getQualityLevels() {
        List<QualityLevel> ret = this.weaponParts.stream().map(ItemPartInstance::getQualityLevel).collect(Collectors.toList());
        return ret;
    }
}