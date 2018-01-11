package com.main.generate.weapon;

import com.main.scripts.ScriptWeapon;
import com.main.stat.StatSet;
import com.main.util.WeightedItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponType implements WeightedItem {

    private final String                            weaponTypeName;
    private final StatSet                           statSet;
    private final WeaponPartType                    primaryPartType;
    private final List<WeaponPartType>              weaponPartTypes;
    private final float                             weight;
    private final ScriptWeapon                      script;

    public WeaponType(String name, float w, StatSet stats, WeaponPartType primary, List<WeaponPartType> types, ScriptWeapon s) {
        this.weaponTypeName = name;
        this.weight = w;
        this.primaryPartType = primary;
        this.weaponPartTypes = types;
        this.statSet = stats;
        this.script = s;
    }
    public String getDisplayName(WeaponInstance weapon) {
        if (this.script == null) return "";
        return this.script.getDisplayName(weapon);
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