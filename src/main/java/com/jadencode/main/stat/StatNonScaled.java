package com.jadencode.main.stat;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatNonScaled extends StatDef {
    public StatNonScaled(String s, double val) {
        super(s, val);
    }
    @Override
    public double scale(int i, double original) {
        return original;
    }
}