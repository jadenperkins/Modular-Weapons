package com.jadencode.main.util;

import com.jadencode.main.nbt.NBTTagCompound;

/**
 * Created by Jaden on 7/23/2014.
 */
public interface NBTTaggable
{
    public void writeToNBT(NBTTagCompound nbt);
    public void readFromNBT(NBTTagCompound nbt);
}