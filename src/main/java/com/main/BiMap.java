package com.main;

import java.util.HashMap;

public class BiMap<K1, K2, V> extends HashMap<K1, HashMap<K2, V>> {
	public V get(K1 key1, K2 key2) {
		return !this.containsKey(key1) ? null : get(key1).getOrDefault(key2, null);
	}
	public V put(K1 key1, K2 key2, V value) {
		if (!this.containsKey(key1)) this.put(key1, new HashMap<>());
		HashMap<K2, V> inner = this.get(key1);
		V ret = inner.put(key2, value);
		this.put(key1, inner);
		return ret;
	}
}