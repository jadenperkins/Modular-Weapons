package com.jadencode.main.stat;
/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatBase<T extends Stat> {

    public static final StatBase<StatFloat> DAMAGE_SLASH = new StatBase("slashDamage", new StatFloat(0F));
    public static final StatBase<StatFloat> DAMAGE_PIERCE = new StatBase("pierceDamage", new StatFloat(0F));
    public static final StatBase<StatFloat> DAMAGE_BLUNT = new StatBase("bluntDamage", new StatFloat(0F));

    private final T defaultValue;
    private final String statName;

    public StatBase(String s, T val) {
        this.defaultValue = val;
        this.statName = s;
    }
    public T getDefaultValue() {
        return this.defaultValue;
    }
    public String getStatName() {
        return this.statName;
    }
}
