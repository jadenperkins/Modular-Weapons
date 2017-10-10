package com.main.generate;

import com.main.nbt.NBTTagCompound;

import java.util.Random;

/**
 * Created by Jaden on 1/19/2015.
 */
public abstract interface Generator<T> {

    public T generate(Random r, int level);
    public void writeNBT(NBTTagCompound nbt);
    public void readNBT(NBTTagCompound nbt);
    public void onCreated(Random r);
}
