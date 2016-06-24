package com.jadencode.main.generate;

import java.util.Random;

/**
 * Created by Jaden on 1/19/2015.
 */
public interface Generator<T> {

    T generate(Random r, int level);
//    public void writeNBT(NBTTagCompound nbt);
//    public void readNBT(NBTTagCompound nbt);
//    public void onCreated(Random r);
}
