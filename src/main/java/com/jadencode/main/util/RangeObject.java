package com.jadencode.main.util;

/**
 * Created by Jaden on 7/24/2015.
 */
public class RangeObject<T> {
    private final int rangeMin;
    private final int rangeMax;
    private final T rangeObj;

    public RangeObject(int min, int max, T obj) {
        this.rangeMin = min;
        this.rangeMax = max;
        this.rangeObj = obj;
    }
    public RangeObject(int min, T obj) {
        this(min, min, obj);
    }
    public int getRangeMax() {
        return rangeMax;
    }
    public int getRangeMin() {
        return rangeMin;
    }
    public T getRangeObj() {
        return rangeObj;
    }
}