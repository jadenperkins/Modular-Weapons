package com.jadencode.main.generate.weapon;

import com.jadencode.main.stat.*;

/**
 * Created by Jaden on 9/11/2015.
 */
public class WeaponClassSword extends WeaponClass {

    private static final String[] partKeys = new String[]{WeaponGenerator.SWORD_BLADES_KEY, WeaponGenerator.SWORD_GRIPS_KEY, WeaponGenerator.SWORD_HILTS_KEY};


    private static final StatSet STAT_SET = new StatSet()
            .add(StatBase.DAMAGE_SLASH, new StatFloat(50F))
            .add(StatBase.DAMAGE_PIERCE, new StatFloat(10F))
            .add(StatBase.DAMAGE_BLUNT, new StatFloat(0F));

    public WeaponClassSword() {
        super("Sword", 1, 1, 0.05F, STAT_SET, WeaponClass.mapParts(partKeys));
    }

    @Override
    public StatSet determineStats(WeaponInstance instance) {
        StatSet baseStats = this.scaledStats(instance.getLevel());
        instance.getWeaponParts().values()
                .forEach(part -> baseStats.getStatsRaw().keySet()
                        .forEach(stat -> baseStats.add(stat, baseStats.get(stat).add(part.getStatSet().get(stat)))));
        return baseStats;
    }
    private StatSet scaledStats(int i) {
        StatSet set = new StatSet();
        float scale = (float) Math.pow(1.1F, i - 1);
        set.addVal(StatBase.DAMAGE_SLASH, this.getStatSet().get(StatBase.DAMAGE_SLASH).as(StatFloat.class).get() * scale);
        set.addVal(StatBase.DAMAGE_PIERCE, this.getStatSet().get(StatBase.DAMAGE_PIERCE).as(StatFloat.class).get() * scale);
        set.addVal(StatBase.DAMAGE_BLUNT, this.getStatSet().get(StatBase.DAMAGE_BLUNT).as(StatFloat.class).get() * scale);
        return set;
    }
}