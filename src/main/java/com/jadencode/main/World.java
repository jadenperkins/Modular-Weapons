package com.jadencode.main;

import java.util.Random;

/**
 * Created by Jaden on 6/5/2015.
 */
public class World {

    private final Random WORLD_RNG;
    private final long seed;

    public World(long seed) {
        this.seed = seed;
        this.WORLD_RNG = new Random(seed);
    }

    public World() {
        this(System.currentTimeMillis());
    }

    public Random getRNG() {
        return this.WORLD_RNG;
    }

    public long getSeed() {
        return this.seed;
    }
}