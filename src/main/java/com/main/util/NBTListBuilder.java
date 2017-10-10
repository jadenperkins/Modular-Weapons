package com.main.util;


import com.main.nbt.NBTBase;
import com.main.nbt.NBTTagList;

/**
 * Created by Jaden on 6/29/2014.
 */
public class NBTListBuilder {
    private NBTTagList nbtTagList;

    public NBTListBuilder() {
        this(new NBTTagList());
    }

    public NBTListBuilder(NBTTagList list) {
        this.nbtTagList = list;
    }
    public NBTTagList get() {
        return this.nbtTagList;
    }
    public NBTListBuilder append(NBTBase nbt) {
        this.get().appendTag(nbt);
        return this;
    }
}