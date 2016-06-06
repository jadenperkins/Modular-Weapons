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

        StatSet stats = this.scaledStats(instance.getLevel());

        for (WeaponPartInstance part : instance.getWeaponParts().values()) {
            StatSet set = part.getStatSet();
            for(StatBase stat : stats.getStatsRaw().keySet()) {
                if(stat.getDefaultValue().isFloat()) {
                    stats.add(stat, stats.getFloat(stat) + set.getFloat(stat));
                }
                if(stat.getDefaultValue().isInteger()) {
                    stats.add(stat, stats.getInt(stat) + set.getInt(stat));
                }
            }
        }
        return stats;
    }
    private StatSet scaledStats(int i) {
        StatSet set = new StatSet();
        float scale = (float) Math.pow(1.1F, i - 1);
        set.add(StatBase.DAMAGE_SLASH, new StatFloat(this.getStatSet().get(StatBase.DAMAGE_SLASH).getAsFloat().getValue() * scale));
        set.add(StatBase.DAMAGE_PIERCE, new StatFloat(this.getStatSet().get(StatBase.DAMAGE_PIERCE).getAsFloat().getValue() * scale));
        set.add(StatBase.DAMAGE_BLUNT, new StatFloat(this.getStatSet().get(StatBase.DAMAGE_BLUNT).getAsFloat().getValue() * scale));
        return set;
    }
}