package com.main.util;

import com.main.nbt.NBTTagCompound;

public interface NBTTagger<T>
{
	public NBTTagCompound toNBT(T object);
	public T fromNBT(NBTTagCompound nbt);
}
