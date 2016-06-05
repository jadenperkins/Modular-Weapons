package com.jadencode.main.util;

import com.jadencode.main.nbt.NBTTagCompound;

/**
 * Created by Jaden on 1/7/2015.
 */
public interface NBTAdapter<T>
{
    public NBTTagCompound getTagCompound();
    public boolean hasTagCompound();
    public T getObject();
}