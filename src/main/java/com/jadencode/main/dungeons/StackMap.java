package com.jadencode.main.dungeons;

import java.util.HashMap;

public class StackMap<K1, K2, V> extends HashMap<K1, HashMap<K2, V>>
{
	private HashMap<K1, HashMap<K2, V>> map = new HashMap<K1, HashMap<K2, V>>();
	
	public void put(K1 k1, K2 k2, V v)
	{
		HashMap<K2, V> inner = map.get(k1);
		if(inner == null)
		{
			inner = new HashMap<K2, V>();
		}
		inner.put(k2, v);
		this.map.put(k1, inner);
	}
	public V get(K1 k1, K2 k2)
	{
		HashMap<K2, V> inner = this.map.get(k1);
		if(inner == null)
		{
			return null;
		}
		return inner.get(k2);
	}
	public String toString()
	{
		return this.map.toString();
	}
}