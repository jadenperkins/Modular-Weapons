package com.jadencode.main.generate.item;

/**
 * Created by JPERKI8 on 6/29/2016.
 */
public class Joint {
    private final String name;
    private final double x;
    private final double y;

    public Joint(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String getName() {
        return name;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}