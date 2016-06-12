package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatDef;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class Stats {
    public static final BiFunction<Integer, Float, Float> SCALE_LEVEL = (i, t) -> t * (float) Math.pow(1.1F, i - 1);
    public static final BiFunction<Float, Float, Float> COMBINE_FLOAT = (a, b) -> a + b;
    public static final BiFunction<Float, Float, Float> MODIFY_FLOAT = (a, b) -> a * b;

    public static final StatBase<Float> DAMAGE_SLASH = new StatDef<>("damageSlash", 0F, SCALE_LEVEL, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> DAMAGE_PIERCE = new StatDef<>("damagePierce", 0F, SCALE_LEVEL, COMBINE_FLOAT, MODIFY_FLOAT);
    public static final StatBase<Float> DAMAGE_BLUNT = new StatDef<>("damageBlunt", 0F, SCALE_LEVEL, COMBINE_FLOAT, MODIFY_FLOAT);

}
