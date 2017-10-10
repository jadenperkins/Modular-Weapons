package com.main;

import java.util.Random;

/**
 * Created by Jaden on 6/5/2015.
 */
public class World {
    private final Random random;
    private final long seed;

    public World(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }
    public World() {
        this(System.currentTimeMillis());
    }
    public Random getRandom() {
        return this.random;
    }
    public long getSeed() {
        return this.seed;
    }
}