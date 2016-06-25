package com.jadencode.main.generate.item;

import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponType implements WeightedItem {

    private final String                            weaponTypeName;
    private final StatSet                           statSet;
    private final WeaponPartType primaryPartType;
    private final List<WeaponPartType>              weaponPartTypes;
    private final float                             weight;
    private final ScriptItem script;

    public WeaponType(String name, float w, StatSet stats, WeaponPartType primary, List<WeaponPartType> types, ScriptItem s) {
        this.weaponTypeName = name;
        this.weight = w;
        this.primaryPartType = primary;
        this.weaponPartTypes = types;
        this.statSet = stats;
        this.script = s;
    }
    public String getDisplayName(WeaponInstance weapon) {
        return "I am obsolete";
//        return this.script.getDisplayName(weapon);
    }
    public String getWeaponTypeName() {
        return weaponTypeName;
    }
    public WeaponPartType getPrimaryPartType() {
        return primaryPartType;
    }
    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet determineStats(WeaponInstance instance) {
        List<StatSet> others = instance.getPartsList().stream().map(WeaponPartInstance::getStats).collect(Collectors.toList());
        StatSet baseStats = this.getStatSet().scaled(instance.getLevel()).combine(others);
        return baseStats;
    }
    public List<WeaponPartType> getWeaponPartTypes() {
        return this.weaponPartTypes;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }
}