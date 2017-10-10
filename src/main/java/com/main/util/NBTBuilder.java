package com.main.util;

import com.main.nbt.NBTTagCompound;
import com.main.nbt.NBTTagList;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Jaden on 6/29/2014.
 */
public class NBTBuilder {
    private NBTTagCompound nbtTagCompound;

    public NBTBuilder() {
        this.nbtTagCompound = new NBTTagCompound();
    }

    public NBTBuilder(NBTTagCompound nbt) {
        this.nbtTagCompound = nbt;
    }

    public NBTTagCompound get() {
        return this.nbtTagCompound;
    }

    public NBTBuilder removeTag(String s) {
        this.get().removeTag(s);
        return this;
    }

    public NBTBuilder setBoolean(String s, boolean value) {
        this.get().setBoolean(s, value);
        return this;
    }

    public NBTBuilder setByte(String s, byte value) {
        this.get().setByte(s, value);
        return this;
    }

    public NBTBuilder setShort(String s, short value) {
        this.get().setShort(s, value);
        return this;
    }

    public NBTBuilder setInteger(String s, int value) {
        this.get().setInteger(s, value);
        return this;
    }

    public NBTBuilder setLong(String s, long value) {
        this.get().setLong(s, value);
        return this;
    }

    public NBTBuilder setDouble(String s, double value) {
        this.get().setDouble(s, value);
        return this;
    }

    public NBTBuilder setFloat(String s, float value) {
        this.get().setFloat(s, value);
        return this;
    }

    public NBTBuilder setIntArray(String s, int[] value) {
        this.get().setIntArray(s, value);
        return this;
    }

    public NBTBuilder setByteArray(String s, byte[] value) {
        this.get().setByteArray(s, value);
        return this;
    }

    public NBTBuilder setString(String s, String value) {
        this.get().setString(s, value);
        return this;
    }

    public NBTBuilder setTagList(String s, NBTTagList value) {
        this.get().setTag(s, value);
        return this;
    }

    public NBTBuilder setCompoundTag(String s, NBTTagCompound nbt) {
        this.get().setTag(s, nbt);
        return this;
    }

    public NBTBuilder setIntegers(String[] keys, int[] vals) {
        if (keys.length != vals.length) throw new IllegalArgumentException("length of keys and vals must be the same");
        int length = keys.length;
        NBTTagCompound nbtTagCompound = get();
        for (int i = 0; i < length; i++) {
            nbtTagCompound.setInteger(keys[i], vals[i]);
        }
        return this;
    }

    public NBTBuilder setDoubles(String[] keys, double[] vals) {
        if (keys.length != vals.length) throw new IllegalArgumentException("length of keys and vals must be the same");
        int length = keys.length;
        NBTTagCompound nbtTagCompound = get();
        for (int i = 0; i < length; i++) {
            nbtTagCompound.setDouble(keys[i], vals[i]);
        }
        return this;
    }
}