package com.upadvisor.main.util;

import com.upadvisor.main.nbt.NBTTagCompound;

public interface NBTTagger<T>
{
	public NBTTagCompound toNBT(T object);
	public T fromNBT(NBTTagCompound nbt);
}
