package com.main.util;

/**
 * Created by Jaden on 7/3/2014.
 */
public class WeightedRandomObject<T> implements WeightedItem {
    public float itemWeight;
    public T theObject;

    public WeightedRandomObject(float f, T o) {
        this.itemWeight = f;
        this.theObject = o;
    }
    @Override
    public String toString() {
        return theObject.toString() + " of " + this.itemWeight;
    }
    @Override
    public float getWeight() {
        return this.itemWeight;
    }
}