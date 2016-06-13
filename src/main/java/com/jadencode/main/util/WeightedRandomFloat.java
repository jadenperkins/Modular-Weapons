package com.jadencode.main.util;

import java.util.*;

/**
 * Copy of vanilla class, but with floats as the weighting system instead of integers
 */
public class WeightedRandomFloat {
    /**
     * Returns the total weight of all items in a collection.
     */
    public static <T extends Weightable> float getTotalWeight(Collection<T> par0Collection) {
        float f = par0Collection.stream().map(a -> a.getWeight()).reduce(0F, (a, b) -> a + b);
        return f;
    }
    /**
     * Returns a random choice from the input items, with a total weight value.
     */
    public static <T extends Weightable> T getRandomItem(Random par0Random, Collection<T> par1Collection, float par2) {
        if (par2 <= 0) {
            throw new IllegalArgumentException();
        } else {
            if (par1Collection instanceof List) {
                List<T> l = new ArrayList<>();
                l.addAll(par1Collection);
                par1Collection = l;
                Collections.shuffle((List) par1Collection, par0Random);
            }
            float j = par0Random.nextFloat() * par2;
            Iterator<T> iterator = par1Collection.iterator();
            T weightedrandomitem;

            do {
                if (!iterator.hasNext()) {
                    return null;
                }

                weightedrandomitem = iterator.next();
                j -= weightedrandomitem.getWeight();
            }
            while (j >= 0);

            return weightedrandomitem;
        }
    }

    /**
     * Returns a random choice from the input items.
     */
    public static <T extends Weightable> T getRandomItem(Random r, Collection<T> collection) {
        return getRandomItem(r, collection, getTotalWeight(collection));
    }
}
