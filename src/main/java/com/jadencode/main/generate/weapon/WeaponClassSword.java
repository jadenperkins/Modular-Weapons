package com.jadencode.main.generate.weapon;

import com.jadencode.main.stat.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 9/11/2015.
 */
public class WeaponClassSword extends WeaponClass {

    private static final String[] partKeys = new String[]{WeaponGenerator.SWORD_BLADES_KEY, WeaponGenerator.SWORD_GRIPS_KEY, WeaponGenerator.SWORD_HILTS_KEY};


    private static final StatSet STAT_SET = new StatSet()
            .add(StatBase.DAMAGE_SLASH, 50F)
            .add(StatBase.DAMAGE_PIERCE, 10F)
            .add(StatBase.DAMAGE_BLUNT, 0F);

    public WeaponClassSword() {
        super("Sword", 1, 1, 0.05F, STAT_SET, WeaponClass.mapParts(partKeys));
    }

    @Override
    public StatSet determineStats(WeaponInstance instance) {
        // TODO: 6/10/2016 Going to be getting an overhaul, something isn't calculating correctly
        List<StatSet> others = instance.getWeaponParts().values().stream().map(WeaponPartInstance::getStatSet).collect(Collectors.toList());
        StatSet baseStats = this.getStatSet().scaled(instance.getLevel()).combine(others);
        return baseStats;
    }
    private StatSet scaledStats(int i) {
        return this.getStatSet().scaled(i);
    }
}