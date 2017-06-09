package com.jadencode.main.magic;

import com.jadencode.main.util.WeightedRandomFloat;
import com.jadencode.main.util.WeightedRandomObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 2/11/2015.
 */
public class SpellBase {
    private static final HashMap<String, SpellBase> spells = new HashMap<>();
    private static final List<WeightedRandomObject<SpellBase>> weightedReferences = new ArrayList<>();


    private final String name;
    private final String description;
    private final Element element;
    private final Delivery delivery;
    private final float weight;

    public SpellBase(Element element, Delivery delivery, float weight, String desc) {
        String name = element.getAdjective() + " " + delivery.getName();
        if (spells.get(name) != null) {
            throw new IllegalArgumentException(name + " is already in use1");
        }
        this.element = element;
        this.delivery = delivery;
        this.name = name;
        this.description = desc;
        this.weight = weight;
        spells.put(name, this);
        weightedReferences.add(new WeightedRandomObject<>(this.weight, this));
    }

    public static void loadBaseSpells() {
        for (Element element : Element.getElements().values()) {
            for (Delivery delivery : Delivery.getDeliveries().values()) {
                float weight = element.getWeight() * delivery.getWeight();
                String description = String.format("%s of %s magic", delivery.getDescription(), element.getName());
                SpellBase base = new SpellBase(element, delivery, weight, description);
            }
        }
    }

    public static HashMap<String, SpellBase> getBaseSpells() {
        return spells;
    }

    public static List<WeightedRandomObject<SpellBase>> getWeightedReferences() {
        return weightedReferences;
    }

    public static SpellBase getRandom() {
        return getRandom(new Random());
    }

    public static SpellBase getRandom(Random r) {
        return WeightedRandomFloat.getRandomItem(r, weightedReferences).getObject();
    }

    public String getName() {
        return this.name;
    }

    public String getName(Random r) {
        return this.getElement().getName(r);
    }

    public String getDescription() {
        return this.description;
    }

    public Element getElement() {
        return this.element;
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public float getWeight() {
        return this.weight;
    }

    public void generateAttributes(SpellObject spellObject) {
        double baseDamage = 50D * Math.pow(1.07D, spellObject.getLevel() - 1);
        double mod = 1D;//Math.pow(1.01D, 1D / (double)this.getWeight());
        int damage = (int) Math.floor(baseDamage * mod);
        spellObject.getIntegers().put("damage", damage);


        this.getDelivery().generateAttributes(spellObject);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}