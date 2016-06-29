package com.jadencode.main;

import java.util.HashMap;
import java.util.function.Supplier;

public class StackMap<K1, K2, V> extends HashMap<K1, HashMap<K2, V>> {
	private final Supplier<V> defaultSupplier;

	public StackMap(Supplier<V> supplier) {
		this.defaultSupplier = supplier;
	}
	public StackMap() {
		this(() -> null);
	}
	public V get(K1 key1, K2 key2) {
		if(!this.containsKey(key1)) this.put(key1, new HashMap<>());
		if(!this.get(key1).containsKey(key2)) this.get(key1).put(key2, this.defaultSupplier.get());
		return this.get(key1).get(key2);
	}
//	public V get(K1 key1, K2 key2, V def) {
//		return this.get(key1, key2) == null ? def : this.get(key1, key2);
//	}
	public void put(K1 key1, K2 key2, V value) {
		if(!this.containsKey(key1)) this.put(key1, new HashMap<>());
		this.get(key1).put(key2, value);
	}
}