package com.jadencode.main.generate.weapon;

import com.jadencode.main.item.StatSet;

/**
 * Created by Jaden on 9/11/2015.
 */
public class WeaponClassSword extends WeaponClass {

    private static final String[] partKeys = new String[]{WeaponGenerator.SWORD_BLADES_KEY, WeaponGenerator.SWORD_GRIPS_KEY, WeaponGenerator.SWORD_HILTS_KEY};


    private static final StatSet STAT_SET = new StatSet(
            new String[]{"slashDamage", "pierceDamage", "bluntDamage", "prepTime", "downTime"},
            new Float[]{50F, /*5F*/ 10F, 0F, 0.5F, 0.5F},
            new String[]{},
            new Integer[]{},
            new String[]{},
            new String[]{}
    );

    public WeaponClassSword() {
        super("Sword", 1, 1, 0.05F, STAT_SET, WeaponClass.mapParts(partKeys));
    }

    @Override
    public StatSet determineStats(WeaponInstance instance) {

        StatSet stats = this.scaledStats(instance.getLevel());

        for (WeaponPartInstance part : instance.getWeaponParts().values()) {
            StatSet set = part.getStatSet();

            for(String key : stats.getFloatKeys()) {
                stats.setFloat(key, stats.getFloat(key) + set.getFloat(key));
            }
            for(String key : stats.getIntegerKeys()) {
                stats.setInteger(key, stats.getInteger(key) + set.getInteger(key));
            }
        }
        return stats;
    }
    private StatSet scaledStats(int i) {
        StatSet set = this.getStatSet().copy();
//        set.setFloat("slashDamage", (float) Math.floor(set.getFloat("slashDamage") * (float) Math.pow(1.1F, i)));
//        set.setFloat("pierceDamage", (float) Math.floor(set.getFloat("pierceDamage") * (float) Math.pow(1.1F, i)));
//        set.setFloat("bluntDamage", (float) Math.floor(set.getFloat("bluntDamage") * (float) Math.pow(1.1F, i)));
        set.setFloat("slashDamage", set.getFloat("slashDamage") * (float) Math.pow(1.1F, i - 1));
        set.setFloat("pierceDamage", set.getFloat("pierceDamage") * (float) Math.pow(1.1F, i - 1));
        set.setFloat("bluntDamage", set.getFloat("bluntDamage") * (float) Math.pow(1.1F, i - 1));
        return set;
    }
}