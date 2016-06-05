package com.jadencode.main.util;

import com.jadencode.main.nbt.NBTTagCompound;

public interface NBTTagger<T>
{
	public NBTTagCompound toNBT(T object);
	public T fromNBT(NBTTagCompound nbt);
}
