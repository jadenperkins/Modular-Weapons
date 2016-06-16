package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatDef;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class Stats {

    public static final StatBase DAMAGE_SLASH = new StatDef("Slash Damage", 0F, true);
    public static final StatBase DAMAGE_PIERCE = new StatDef("Pierce Damage", 0F, true);
    public static final StatBase DAMAGE_BLUNT = new StatDef("Blunt Damage", 0F, true);
    public static final StatBase ATTACK_TIME = new StatDef("Attack Time", 0F, false);
    public static final StatBase RANGE = new StatDef("Range", 0F, false);
    public static final StatBase DRAW_TIME = new StatDef("Draw Time", 0F, false);
}
