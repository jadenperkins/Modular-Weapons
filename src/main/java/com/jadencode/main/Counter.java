package com.jadencode.main;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by Jaden on 2/17/2015.
 */
public class Counter<T> {

    private Map<T, Integer> counts = new HashMap<>();

    public Counter() {

    }

    public void count(T obj) {
        int count = this.getCount(obj);
        counts.put(obj, count + 1);
    }

    public int getCount(T obj) {
        if (!this.counts.containsKey(obj)) {
            return 0;
        }
        return this.counts.get(obj);
    }

    public void displayCounts() {
        for (T obj : this.counts.keySet()) {
            int count = this.getCount(obj);
            System.out.println(String.format("%s = %d", obj.toString(), count));
        }
    }

    public void displayCounts(CounterDisplay<T> display) {
        for (T obj : this.counts.keySet()) {
            int count = this.getCount(obj);
            System.out.println(String.format("%s = %d", display.display(obj), count));
        }
    }

    public void displaySorted() {
        List<CountEntry<T>> entries = new ArrayList<>();
        for (T obj : this.counts.keySet()) {
            entries.add(new CountEntry<>(obj, this.getCount(obj)));
        }
        Collections.sort(entries);
        for (CountEntry<T> entry : entries) {
            System.out.println(String.format("%s = %d", entry.obj.toString(), entry.amount));
        }
    }

    public interface CounterDisplay<T> {
        String display(T t);
    }

    private static class CountEntry<T> implements Comparable<CountEntry> {

        private int amount;
        private T obj;

        public CountEntry(T obj, int amt) {
            this.amount = amt;
            this.obj = obj;
        }

        @Override
        public int compareTo(@NotNull CountEntry o) {
            return Integer.compare(this.amount, o.amount);
        }
    }
}
