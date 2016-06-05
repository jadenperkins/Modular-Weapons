package com.upadvisor.main.item;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {

    private static final String[] ESA = new String[0];

    public static final StatSet DEFAULT = new StatSet(map(ESA, new Float[0]), map(ESA, new Integer[0]), map(ESA, ESA));

    private final HashMap<String, Float>   floats;
    private final HashMap<String, Integer> ints;
    private final HashMap<String, String>  strings;

    public StatSet(HashMap<String, Float> floats, HashMap<String, Integer> ints, HashMap<String, String> strings) {
        this.floats = floats;
        this.ints = ints;
        this.strings = strings;
    }
    public StatSet(String[] floatKeys, Float[] floats, String[] intKeys, Integer[] ints, String[] stringKeys, String[] strings) {
        this(map(floatKeys, floats), map(intKeys, ints), map(stringKeys, strings));
    }
    public Set<String> getFloatKeys() {
        return this.floats.keySet();
    }
    public float getFloat(String key) {
        if(this.floats.containsKey(key)) {
            return this.floats.get(key);
        }
        return 0F;
    }
    public void setFloat(String key, float value) {
        this.floats.put(key, value);
    }
    public Set<String> getIntegerKeys() {
        return this.ints.keySet();
    }
    public int getInteger(String key) {
        if(this.ints.containsKey(key)) {
            return this.ints.get(key);
        }
        return 0;
    }
    public void setInteger(String key, int value) {
        this.ints.put(key, value);
    }
    public Set<String> getStringKeys() {
        return this.strings.keySet();
    }
    public String getString(String key) {
        if(this.strings.containsKey(key)) {
            return this.strings.get(key);
        }
        return "";
    }
    public void setString(String key, String value) {
        this.strings.put(key, value);
    }
    public StatSet copy() {
        HashMap<String, Float> floats = this.copyMap(this.floats);
        HashMap<String, Integer> ints = this.copyMap(this.ints);
        HashMap<String, String> strings = this.copyMap(this.strings);

        StatSet set = new StatSet(floats, ints, strings);
        return set;
    }
    private <T> HashMap<String, T> copyMap(HashMap<String, T> source) {
        HashMap<String, T> copy = new HashMap<>();
        for(String key : source.keySet()) {
            copy.put(key, source.get(key));
        }
        return copy;
    }
    public static <T> HashMap<String, T> map(String[] keys, T[] values) {
        if(keys.length != values.length)
            throw new IllegalArgumentException("Length of keys and values must be equal!");

        HashMap<String, T> map = new HashMap<>();
        for(int i = 0; i < keys.length; i++) {
            String key = keys[i];
            T value = values[i];
            map.put(key, value);
        }
        return map;
    }
}