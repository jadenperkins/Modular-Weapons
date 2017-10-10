package com.main.util;

import com.main.nbt.NBTTagCompound;

public interface NBTTagger<T> {
	NBTTagCompound toNBT(T object);
	T fromNBT(NBTTagCompound nbt);
}
