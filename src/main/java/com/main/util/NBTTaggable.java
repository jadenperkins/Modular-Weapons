package com.main.util;

import com.main.nbt.NBTTagCompound;

/**
 * Created by Jaden on 7/23/2014.
 */
public interface NBTTaggable {
    void writeToNBT(NBTTagCompound nbt);
    void readFromNBT(NBTTagCompound nbt);
}