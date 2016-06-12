package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class StatSets {
    public static final StatSet EMPTY = new StatSet();

    public static final StatSet BASE_SWORD = new StatSet()
            .add(Stats.DAMAGE_SLASH, 50F)
            .add(Stats.DAMAGE_PIERCE, 10F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    public static final StatSet GRIP_HEAVY = new StatSet()
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.DAMAGE_PIERCE, 4F)
            .add(Stats.DAMAGE_BLUNT, 1F);
    public static final StatSet GRIP_BASIC = new StatSet()
            .add(Stats.DAMAGE_SLASH, 5F)
            .add(Stats.DAMAGE_PIERCE, 2F)
            .add(Stats.DAMAGE_BLUNT, 0F);
    public static final StatSet GRIP_LIGHT = new StatSet()
            .add(Stats.DAMAGE_SLASH, 0F)
            .add(Stats.DAMAGE_PIERCE, 0F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    public static final StatSet HILT_HEAVY = new StatSet()
            .add(Stats.DAMAGE_SLASH, 16F)
            .add(Stats.DAMAGE_PIERCE, 6F)
            .add(Stats.DAMAGE_BLUNT, 2F);
    public static final StatSet HILT_MEDIUM = new StatSet()
            .add(Stats.DAMAGE_SLASH, 8F)
            .add(Stats.DAMAGE_PIERCE, 3F)
            .add(Stats.DAMAGE_BLUNT, 1F);
    public static final StatSet HILT_LIGHT = new StatSet()
            .add(Stats.DAMAGE_SLASH, 0F)
            .add(Stats.DAMAGE_PIERCE, 0F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    public static final StatSet BLADE_BROAD = new StatSet()
            .add(Stats.DAMAGE_SLASH, 15F)
            .add(Stats.DAMAGE_PIERCE, 15F)
            .add(Stats.DAMAGE_BLUNT, 0F);
    public static final StatSet BLADE_LONG  = new StatSet()
            .add(Stats.DAMAGE_SLASH, 20F)
            .add(Stats.DAMAGE_PIERCE, 10F)
            .add(Stats.DAMAGE_BLUNT, 0F);
    public static final StatSet BLADE_SHORT = new StatSet()
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.DAMAGE_PIERCE, 5F)
            .add(Stats.DAMAGE_BLUNT, 0F);
}
