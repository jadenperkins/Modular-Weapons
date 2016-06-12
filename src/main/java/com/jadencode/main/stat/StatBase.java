package com.jadencode.main.stat;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/9/2016.
 */
public interface StatBase<T> {

    BiFunction<Integer, Float, Float> SCALE_LEVEL = (i, t) -> t * (float) Math.pow(1.1F, i - 1);
    BiFunction<Float, Float, Float> COMBINE_FLOAT = (a, b) -> a + b;

    StatBase<Float> DAMAGE_SLASH = new StatDef<>("damageSlash", 0F, SCALE_LEVEL, COMBINE_FLOAT);
    StatBase<Float> DAMAGE_PIERCE = new StatDef<>("damagePierce", 0F, SCALE_LEVEL, COMBINE_FLOAT);
    StatBase<Float> DAMAGE_BLUNT = new StatDef<>("damageBlunt", 0F, SCALE_LEVEL, COMBINE_FLOAT);

    Stat<T> from(T val);
    Stat<T> getDefaultValue();
    Stat<T> scale(int i, Stat<T> original);
    Stat<T> combine(Stat<T> first, Stat<T> second);
    String getStatName();
}