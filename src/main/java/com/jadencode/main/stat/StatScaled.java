package com.jadencode.main.stat;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatScaled extends StatDef {
    public StatScaled(String s, double val) {
        super(s, val);
    }
    @Override
    public double scale(int i, double original) {
        return original * Math.pow(1.1D, i - 1);
    }
}