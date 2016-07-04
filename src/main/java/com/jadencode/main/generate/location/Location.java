package com.jadencode.main.generate.location;

/**
 * Created by Jaden on 1/19/2015.
 */
public class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}