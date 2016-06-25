package com.jadencode.main.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copy of vanilla class, but with floats as the weighting system instead of integers
 */
public class WeightedRandomFloat {
    /**
     * Returns the total weight of all content in a collection.
     */
    public static <T extends WeightedItem> float getTotalWeight(Collection<T> weightedItems) {
        return weightedItems.stream().map(a -> a.getWeight()).reduce(0F, (a, b) -> a + b);
    }
    /**
     * Returns a random choice consume the input content, with a total weight value.
     */
    public static <T extends WeightedItem> T getRandomItem(Random rng, Collection<T> weightedItems, float weight) {
        if(weightedItems.isEmpty()) throw new IllegalArgumentException("Must have at lest 1 item!");
        if(weight <= 0) throw new IllegalArgumentException("Weight must be greater than 0!");
        List<T> asList = weightedItems.stream().collect(Collectors.toList());
        Collections.shuffle(asList);
        weightedItems = asList;

        float threshold = rng.nextFloat() * weight;

        for (T item : weightedItems) {
            threshold -= item.getWeight();
            if(threshold <= 0) return item;
        }
        return null;
    }
    /**
     * Returns a random choice consume the input content.
     */
    public static <T extends WeightedItem> T getRandomItem(Random r, Collection<T> collection) {
        return getRandomItem(r, collection, getTotalWeight(collection));
    }
}
