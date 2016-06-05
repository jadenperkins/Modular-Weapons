package com.upadvisor.main;

import java.util.HashMap;

public class StackMap<K1, K2, V> extends HashMap<K1, HashMap<K2, V>>
{
	public V get(K1 key1, K2 key2)
	{
		HashMap<K2, V> inner = this.get(key1);
		if(inner == null)
		{
			inner = new HashMap<K2, V>();
		}
		V ret = inner.get(key2);
		return ret;
	}
	public void put(K1 key1, K2 key2, V value)
	{
		HashMap<K2, V> inner = this.get(key1);
		if(inner == null)
		{
			inner = new HashMap<K2, V>();
		}
		inner.put(key2, value);
		this.put(key1, inner);
	}

}