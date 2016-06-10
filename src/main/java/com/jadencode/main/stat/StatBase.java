package com.jadencode.main.stat;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/9/2016.
 */
public interface StatBase<T> {

    StatBase<Float> DAMAGE_SLASH = new StatDef<>("damageSlash", new StatFloat(0F), a -> new StatFloat(a), (i, s) -> new StatFloat(s.get() * (float) Math.pow(1.1F, i - 1)));
    StatBase<Float> DAMAGE_PIERCE = new StatDef<>("damagePierce", new StatFloat(0F), a -> new StatFloat(a), (i, s) -> new StatFloat(s.get() * (float) Math.pow(1.1F, i - 1)));
    StatBase<Float> DAMAGE_BLUNT = new StatDef<>("damageBlunt", new StatFloat(0F), a -> new StatFloat(a), (i, s) -> new StatFloat(s.get() * (float) Math.pow(1.1F, i - 1)));

    Stat<T> from(T val);
    Stat<T> getDefaultValue();
    Stat<T> scale(int i, Stat<T> original);
    String getStatName();
}