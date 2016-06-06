package com.jadencode.main.stat;
/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatBase<T extends Stat> {

    public static final StatBase<StatFloat> DAMAGE_SLASH = new StatBase<>("slashDamage");
    public static final StatBase<StatFloat> DAMAGE_PIERCE = new StatBase<>("pierceDamage");
    public static final StatBase<StatFloat> DAMAGE_BLUNT = new StatBase<>("bluntDamage");


    private final String statName;

    public StatBase(String s) {
        this.statName = s;
    }
    public String getStatName() {
        return this.statName;
    }
}
