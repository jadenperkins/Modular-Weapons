package com.main;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 2/17/2015.
 */
public class Counter<T> {
    private final Map<T, Integer> counts = new HashMap<>();
    private final Function<T, String> defaultDisplay = t -> t.toString();

    public void count(T obj) {
        int count = this.getCount(obj);
        counts.put(obj, count + 1);
    }

    public int getCount(T obj) {
        if(!this.counts.containsKey(obj)) return 0;
        return this.counts.get(obj);
    }
    public void displayCounts() {
        this.displayCounts(defaultDisplay);
    }
    public void displayCounts(Function<T, String> display) {
        Set<T> keys = this.counts.keySet();
        keys.stream().map(key -> String.format("%s = %d", display.apply(key), getCount(key))).forEach(System.out::println);
    }
    public void displaySorted() {
        displaySorted(defaultDisplay);
    }
    public void displaySorted(Function<T, String> display) {
        Set<T> keys = counts.keySet();
        List<CountEntry<T>> entries = keys.stream().map(key -> new CountEntry<>(key, getCount(key))).collect(Collectors.toList());
        entries.sort(null);
        entries.stream().map(entry -> String.format("%s = %d", display.apply(entry.get()), entry.getAmount())).forEach(System.out::println);
    }




    private static class CountEntry<T> implements Comparable<CountEntry> {
        private final int amount;
        private final T obj;

        public CountEntry(T obj, int amt) {
            this.amount = amt;
            this.obj = obj;
        }
        public T get() {
            return obj;
        }
        public int getAmount() {
            return amount;
        }

        @Override
        public int compareTo(CountEntry o) {
            return Integer.compare(this.amount, o.amount);
        }
    }
}
