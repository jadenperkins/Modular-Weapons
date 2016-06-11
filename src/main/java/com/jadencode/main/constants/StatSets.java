package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class StatSets {
    public static final StatSet EMPTY = new StatSet();

    public static final StatSet SWORD_BASE_STATS = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 50F)
            .addVal(StatBase.DAMAGE_PIERCE, 10F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);
}
