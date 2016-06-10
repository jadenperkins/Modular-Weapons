package com.jadencode.main.generate.weapon;

import com.jadencode.main.stat.*;

/**
 * Created by Jaden on 9/11/2015.
 */
public class WeaponClassSword extends WeaponClass {

    private static final String[] partKeys = new String[]{WeaponGenerator.SWORD_BLADES_KEY, WeaponGenerator.SWORD_GRIPS_KEY, WeaponGenerator.SWORD_HILTS_KEY};


    private static final StatSet STAT_SET = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 50F)
            .addVal(StatBase.DAMAGE_PIERCE, 10F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);

    public WeaponClassSword() {
        super("Sword", 1, 1, 0.05F, STAT_SET, WeaponClass.mapParts(partKeys));
    }

    @Override
    public StatSet determineStats(WeaponInstance instance) {
        StatSet baseStats = this.scaledStats(instance.getLevel());
        instance.getWeaponParts().values().forEach(part -> baseStats.combine(part.getStatSet()));
        return baseStats;
    }
    private StatSet scaledStats(int i) {
        StatSet set = new StatSet();
        float scale = (float) Math.pow(1.1F, i - 1);

        set.copyVal(StatBase.DAMAGE_SLASH, this.getStatSet(), a -> a * scale);
        set.copyVal(StatBase.DAMAGE_PIERCE, this.getStatSet(), a -> a * scale);
        set.copyVal(StatBase.DAMAGE_BLUNT, this.getStatSet(), a -> a * scale);
        return set;
    }
}