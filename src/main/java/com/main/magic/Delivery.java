package com.main.magic;

import java.util.HashMap;

/**
 * Created by Jaden on 2/11/2015.
 */
public class Delivery {
    private static HashMap<String, Delivery> deliveries = new HashMap<>();

    public static final Delivery projectile = new Delivery("Projectile", 1.0F, "A concentrated projectile");
    public static final Delivery explosion  = new Delivery("Explosion", 0.16F, "A large blast, centered on who uses it,");
    public static final Delivery nova       = new Delivery("Nova", 0.36F, "A large blast, centered on where it detonates,");
    public static final Delivery blast      = new Delivery("Blast", 0.64F, "A devastating projectile");
    public static final Delivery focus      = new Delivery("Focus", 0.81F, "A constant stream");
    public static final Delivery crescent   = new Delivery("Crescent", 0.29F, "A deadly sweep");
    public static final Delivery surge      = new Delivery("Surge", 0.49F, "A dangerous wave");
    public static final Delivery charge     = new Delivery("Charge", 0.1F, "A rapid forward movement, causing damage to anything in the way");

    private final String name;
    private final String description;
    private final float  weight;

    public Delivery(String name, float weight, String desc) {
        if (deliveries.get(name) != null) {
            throw new IllegalArgumentException(name + " is already in use!");
        }
        this.name = name;
        this.description = desc;
        this.weight = weight;
        deliveries.put(name, this);
    }
    public String getName() {
        return name;
    }
    public String getDescription() { return this.description; }
    public float getWeight() {
        return this.weight;
    }
    public void generateAttributes(SpellObject spellObject) {
    }
    public static HashMap<String, Delivery> getDeliveries() {
        return deliveries;
    }
}