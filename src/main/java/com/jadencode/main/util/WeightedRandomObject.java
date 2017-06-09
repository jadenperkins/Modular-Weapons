package com.jadencode.main.util;

/**
 * Created by JPERKI8 on 6/9/2017.
 */
public class WeightedRandomObject<T> implements WeightedItem{
    private final T object;
    private final float weight;

    public WeightedRandomObject(float weight, T object) {
        this.weight = weight;
        this.object = object;
    }
    public T getObject() {
        return object;
    }
    public float getWeight() {
        return weight;
    }
}
