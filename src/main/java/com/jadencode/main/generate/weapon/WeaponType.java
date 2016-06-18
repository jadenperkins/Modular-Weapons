package com.jadencode.main.generate.weapon;

import com.jadencode.main.scripts.ScriptWeapon;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponType implements WeightedItem {

    private final String weaponTypeName;
    private final StatSet                           statSet;
    private final List<WeaponPartType>              weaponPartTypes;
    private final float                             weight;
    private final ScriptWeapon                      script;

    public WeaponType(String name, float w, StatSet stats, List<WeaponPartType> types, ScriptWeapon s) {
        this.weaponTypeName = name;
        this.weight = w;
        this.weaponPartTypes = types;
        this.statSet = stats;
        this.script = s;
    }
    public String getDisplayName(WeaponInstance weapon) {
        return this.script.getDisplayName(weapon);
    }
    public String getWeaponTypeName() {
        return weaponTypeName;
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