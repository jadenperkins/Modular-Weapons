package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponPartType;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.material.MaterialLibrary;
import com.jadencode.main.material.MaterialResource;

/**
 * Created by Jaden on 9/17/2015.
 */
public class WeaponPartSword extends WeaponPartBase {
    public WeaponPartSword(String name, String mod, float weight, StatSet stats, WeaponPartType type, MaterialLibrary... mats) {
        super(name, mod, weight, stats, type, mats);
    }
    public WeaponPartSword(String name, String mod, StatSet stats, WeaponPartType type, MaterialLibrary... mats) {
        this(name, mod, 1F, stats, type, mats);
    }
    @Override
    public StatSet modifyStats(MaterialResource resource) {
        StatSet set = this.getStatSet().copy();
        set.modVal(StatBase.DAMAGE_SLASH, resource.getMultiplier());
        set.modVal(StatBase.DAMAGE_PIERCE, resource.getMultiplier());
        set.modVal(StatBase.DAMAGE_BLUNT, resource.getMultiplier());
        return set;
    }
}