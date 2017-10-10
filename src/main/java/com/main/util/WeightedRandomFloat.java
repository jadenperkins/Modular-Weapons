package com.main.util;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedRandomFloat {
    public static <T extends WeightedItem> float getTotalWeight(Collection<T> weightedItems) {
        return weightedItems.stream().map(a -> a.getWeight()).reduce(0F, (a, b) -> a + b);
    }
    public static <T extends WeightedItem> T getRandomItem(Random r, Collection<T> collection) {
        List<T> list = new ArrayList<>(collection);
        Collections.shuffle(list);
        return getRandomItem(r, list);
    }
    public static <T extends WeightedItem> T getRandomItem(Random r, List<T> collection) {
        return getRandomItem(r, collection, getTotalWeight(collection));
    }
    public static <T extends WeightedItem> T getRandomItem(Random rng, List<T> weightedItems, float weight) {
        if(weightedItems.isEmpty()) throw new IllegalArgumentException("Must have at least 1 item!");
        if(weight <= 0) throw new IllegalArgumentException("Weight must be greater than 0!");

        float threshold = rng.nextFloat() * weight;

        for (T item : weightedItems) {
            threshold -= item.getWeight();
            if(threshold <= 0) return item;
        }
        return null;
    }
}
