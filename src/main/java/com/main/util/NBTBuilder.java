package com.main.util;

import com.main.nbt.NBTTagCompound;
import com.main.nbt.NBTTagList;

/**
 * Created by Jaden on 6/29/2014.
 */
public class NBTBuilder
{
    private NBTTagCompound nbtTagCompound;

    public NBTBuilder() {
        this.nbtTagCompound = new NBTTagCompound();
    }

    public NBTBuilder(NBTTagCompound nbt) {
        this.nbtTagCompound = nbt;
    }

    public NBTTagCompound get()
    {
        return this.nbtTagCompound;
    }
    public NBTBuilder removeTag(String s)
    {
        this.nbtTagCompound.removeTag(s);
        return this;
    }
    public NBTBuilder setBoolean(String s, boolean value)
    {
        this.nbtTagCompound.setBoolean(s, value);
        return this;
    }
    public NBTBuilder setByte(String s, byte value)
    {
        this.nbtTagCompound.setByte(s, value);
        return this;
    }
    public NBTBuilder setShort(String s, short value)
    {
        this.nbtTagCompound.setShort(s, value);
        return this;
    }
    public NBTBuilder setInteger(String s, int value)
    {
        this.nbtTagCompound.setInteger(s, value);
        return this;
    }
    public NBTBuilder setLong(String s, long value)
    {
        this.nbtTagCompound.setLong(s, value);
        return this;
    }
    public NBTBuilder setDouble(String s, double value)
    {
        this.nbtTagCompound.setDouble(s, value);
        return this;
    }
    public NBTBuilder setFloat(String s, float value)
    {
        this.nbtTagCompound.setFloat(s, value);
        return this;
    }
    public NBTBuilder setIntArray(String s, int[] value)
    {
        this.nbtTagCompound.setIntArray(s, value);
        return this;
    }
    public NBTBuilder setByteArray(String s, byte[] value)
    {
        this.nbtTagCompound.setByteArray(s, value);
        return this;
    }
    public NBTBuilder setString(String s, String value)
    {
        this.nbtTagCompound.setString(s, value);
        return this;
    }
    public NBTBuilder setTagList(String s, NBTTagList value) {
        this.nbtTagCompound.setTag(s, value);
        return this;
    }
    public NBTBuilder setCompoundTag(String s, NBTTagCompound nbt)
    {
        this.nbtTagCompound.setTag(s, nbt);
        return this;
    }
    public NBTBuilder setIntegers(String[] keys, int[] vals)
    {
        for(int i = 0; i < keys.length; i++)
        {
            this.nbtTagCompound.setDouble(keys[i], vals[i]);
        }
        return this;
    }
    public NBTBuilder setDoubles(String[] keys, double[] vals)
    {
        for(int i = 0; i < keys.length; i++)
        {
            this.nbtTagCompound.setDouble(keys[i], vals[i]);
        }
        return this;
    }
}