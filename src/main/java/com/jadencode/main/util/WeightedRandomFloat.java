package com.jadencode.main.util;

import java.util.*;

/**Copy of vanilla class, but with floats as the weighting system instead of integers*/
public class WeightedRandomFloat
{
    /**
     * Returns the total weight of all items in a collection.
     */
    public static <T extends WeightedRandomObject> float getTotalWeight(Collection<T> par0Collection)
    {
        float f = 0;
        for(WeightedRandomObject object : par0Collection)
        {
            f += object.itemWeight;
        }
        return f;
    }
    public static <T extends Weightable> float getTotalWeightable(Collection<T> par0Collection)
    {
        float f = 0;
        for(Weightable object : par0Collection)
        {
            f += object.getWeight();
        }
        return f;
    }

    /**
     * Returns a random choice from the input items, with a total weight value.
     */
    public static <T extends WeightedRandomObject> T getRandomItem(Random par0Random, Collection<T> par1Collection, float par2)
    {
        if (par2 <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
        	if(par1Collection instanceof List)
        	{
        		Collections.shuffle((List)par1Collection, par0Random);
        	}
            float j = par0Random.nextFloat() * par2;
            Iterator<T> iterator = par1Collection.iterator();
            T weightedrandomitem;

            do
            {
                if (!iterator.hasNext())
                {
                    return null;
                }

                weightedrandomitem = iterator.next();
                j -= weightedrandomitem.itemWeight;
            }
            while (j >= 0);

            return weightedrandomitem;
        }
    }
    public static <T extends Weightable> T getRandomWeightable(Random par0Random, Collection<T> par1Collection, float par2)
    {
        if (par2 <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            if(par1Collection instanceof List)
            {   List<T> l = new ArrayList<>();
                l.addAll(par1Collection);
                par1Collection = l;
                Collections.shuffle((List)par1Collection, par0Random);
            }
            float j = par0Random.nextFloat() * par2;
            Iterator<T> iterator = par1Collection.iterator();
            T weightedrandomitem;

            do
            {
                if (!iterator.hasNext())
                {
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
    public static <T extends WeightedRandomObject> T getRandomItem(Random par0Random, Collection<T> par1Collection)
    {
        return getRandomItem(par0Random, par1Collection, getTotalWeight(par1Collection));
    }
    public static <T extends Weightable> T getRandomWeightable(Random r, Collection<T> collection) {
        return getRandomWeightable(r, collection, getTotalWeightable(collection));
    }
}
