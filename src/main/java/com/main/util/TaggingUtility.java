package com.main.util;

import com.main.nbt.NBTTagCompound;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TaggingUtility<T> {
    private NBTAdapter<T> adapter;

    public TaggingUtility(NBTAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public boolean hasTag(String tagName) {
        return this.adapter.getTagCompound() != null ? this.adapter.getTagCompound().hasKey(tagName) : false;
    }

    public void removeTag(String tagName) {
        NBTTagCompound nbt = this.adapter.getTagCompound();
        if (nbt != null && nbt.hasKey(tagName)) nbt.removeTag(tagName);
    }

    public boolean getBoolean(String tagName) {
        return hasTag(tagName) ? adapter.getTagCompound().getBoolean(tagName) : false;
    }
    public void setBoolean(String tagName, boolean val) {
        if (this.adapter.getTagCompound() == null) return;
        this.adapter.getTagCompound().setBoolean(tagName, val);
    }
    public void copyBoolean(TaggingUtility util, String tagName) {
        util.setBoolean(tagName, this.getBoolean(tagName));
    }

    public int getInt(String tagName) {
        return hasTag(tagName) ? adapter.getTagCompound().getInteger(tagName) : 0;
    }

    public void setInt(String tagName, int val) {
        if (this.adapter.getTagCompound() == null) return;
        this.adapter.getTagCompound().setInteger(tagName, val);
    }

    public void copyInt(TaggingUtility util, String tagName) {
        util.setInt(tagName, this.getInt(tagName));
    }

    public double getDouble(String tagName) {
        return hasTag(tagName) ? adapter.getTagCompound().getDouble(tagName) : 0D;
    }

    public void setDouble(String tagName, double val) {
        if (this.adapter.getTagCompound() == null) return;
        this.adapter.getTagCompound().setDouble(tagName, val);
    }

    public void copyDouble(TaggingUtility util, String tagName) {
        util.setDouble(tagName, this.getDouble(tagName));
    }

    public String getString(String tagName) {
        return hasTag(tagName) ? adapter.getTagCompound().getString(tagName) : "";
    }

    public void setString(String tagName, String val) {
        if (this.adapter.getTagCompound() == null) return;
        this.adapter.getTagCompound().setString(tagName, val);
    }

    public void copyString(TaggingUtility util, String tagName) {
        util.setString(tagName, this.getString(tagName));
    }

    public int[] getIntArray(String tagName) {
        return hasTag(tagName) ? adapter.getTagCompound().getIntArray(tagName) : new int[0];
    }

    public void setIntArray(String tagName, int[] val) {
        if (this.adapter.getTagCompound() == null) return;
        this.adapter.getTagCompound().setIntArray(tagName, val);
    }

    public double[] getDoubleArray(String tagName) {
        if (!hasTag(tagName)) return new double[0];

        NBTTagCompound nbt = this.adapter.getTagCompound().getCompoundTag(tagName);

        int size = nbt.getInteger("size");
        double[] ret = new double[size];
        for (int i = 0; i < size; i++) ret[i] = nbt.getDouble(i + "");
        return ret;
    }

    public void setDoubleArray(String tagName, double[] val) {
        if (this.adapter.getTagCompound() == null) return;

        int size = val.length;
        NBTTagCompound nbt = new NBTTagCompound();
        for (int i = 0; i < size; i++) nbt.setDouble(i + "", val[i]);
        this.adapter.getTagCompound().setTag(tagName, nbt);
    }

    public void copyDoubleArray(TaggingUtility util, String tagName) {
        util.setDoubleArray(tagName, this.getDoubleArray(tagName));
    }
}