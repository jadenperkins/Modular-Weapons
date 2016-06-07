package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatBase<T> {

    public static final StatBase<Float> DAMAGE_SLASH = new StatBase<>("damageSlash", new StatFloat(0F), a -> new StatFloat(a));
    public static final StatBase<Float> DAMAGE_PIERCE = new StatBase<>("damagePierce", new StatFloat(0F), a -> new StatFloat(a));
    public static final StatBase<Float> DAMAGE_BLUNT = new StatBase<>("damageBlunt", new StatFloat(0F), a -> new StatFloat(a));

    private final Stat<T> defaultValue;
    private final String statName;
    private final Function<T, Stat<T>> generator;

    public StatBase(String s, Stat<T> val, Function<T, Stat<T>> g) {
        this.defaultValue = val;
        this.statName = s;
        this.generator = g;
    }
    public Stat<T> from(T val) {
        return this.generator.apply(val);
    }
    public Stat<T> getDefaultValue() {
        return this.defaultValue;
    }
    public String getStatName() {
        return this.statName;
    }
}
