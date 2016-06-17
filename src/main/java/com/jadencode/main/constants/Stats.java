package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;

import java.util.HashMap;


/**
 * Created by gtrpl on 6/11/2016.
 */
public final class Stats {

    private static final HashMap<String, StatBase> STATS = new HashMap<>();

    public static void register(StatBase stat) {
        STATS.put(stat.getStatName(), stat);
    }
    public static StatBase get(String name) {
        return STATS.get(name);
    }

//    public static final StatBase DAMAGE_SLASH = new StatScaled("Slash Damage", 0F);
//    public static final StatBase DAMAGE_PIERCE = new StatScaled("Pierce Damage", 0F);
//    public static final StatBase DAMAGE_BLUNT = new StatScaled("Blunt Damage", 0F);
//    public static final StatBase ATTACK_TIME = new StatNonScaled("Attack Time", 0F);
//    public static final StatBase RANGE = new StatNonScaled("Range", 0F);
//    public static final StatBase DRAW_TIME = new StatNonScaled("Draw Time", 0F);
}
