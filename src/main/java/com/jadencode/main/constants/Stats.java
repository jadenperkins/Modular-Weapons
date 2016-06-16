package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatDef;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class Stats {
    public static final BiFunction<Integer, Float, Float> SCALE_LEVEL = (i, t) -> t * (float) Math.pow(1.1F, i - 1);
    public static final BiFunction<Integer, Float, Float> SCALE_NONE = (i, t) -> t;
    public static final BiFunction<Float, Float, Float> COMBINE_FLOAT = (a, b) -> a + b;
    public static final BiFunction<Float, Float, Float> MODIFY_FLOAT = (a, b) -> a * b;

    public static final StatBase<Float> DAMAGE_SLASH = new StatDef<>("Slash Damage", 0F, SCALE_LEVEL, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> DAMAGE_PIERCE = new StatDef<>("Pierce Damage", 0F, SCALE_LEVEL, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> DAMAGE_BLUNT = new StatDef<>("Blunt Damage", 0F, SCALE_LEVEL, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> ATTACK_TIME = new StatDef<>("Attack Time", 0F, SCALE_NONE, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> RANGE = new StatDef<>("Range", 0F, SCALE_NONE, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> DRAW_TIME = new StatDef<>("Draw Time", 0F, SCALE_NONE, COMBINE_FLOAT, MODIFY_FLOAT);
}
