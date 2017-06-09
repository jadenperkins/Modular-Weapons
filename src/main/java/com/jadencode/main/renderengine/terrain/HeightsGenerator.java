package com.jadencode.main.renderengine.terrain;

import java.util.Random;

/**
 * Created by gtrpl on 7/8/2016.
 */
public class HeightsGenerator {
    private static final float AMPLITUDE = 30F;
    private static final int OCTAVES = 3;
    private static final float ROUGHNESS = 0.1F;

    private final long seed;
    private final Random RNG;
    private final int xOffset;
    private final int zOffset;

    public HeightsGenerator(int gridX, int gridZ, int vertexCount, long seed) {
        this.seed = seed;
        this.xOffset = gridX * (vertexCount - 1);
        this.zOffset = gridZ * (vertexCount - 1);
        this.RNG = new Random(seed);
    }
    public float generateHeight(int x, int z) {
        float total = 0;
        float d = (float) Math.pow(2, OCTAVES - 1);
        for(int i = 0; i < OCTAVES; i++) {
            float freq = (float) (Math.pow(2, i) / d);
            float amp = (float) Math.pow(ROUGHNESS, i) * AMPLITUDE;
            total += this.getInterpolatedNoise((x + this.xOffset) * freq, (z + this.zOffset) * freq) * amp;
        }
        return total;
    }
    private float getInterpolatedNoise(float x, float z) {
        int intX = (int) x;
        int intZ = (int) z;
        float fracX = x - intX;
        float fracZ = z - intZ;
        float v1 = getSmoothNoise(intX, intZ);
        float v2 = getSmoothNoise(intX + 1, intZ);
        float v3 = getSmoothNoise(intX, intZ + 1);
        float v4 = getSmoothNoise(intX + 1, intZ + 1);
        float i1 = interpolate(v1, v2, fracX);
        float i2 = interpolate(v3, v4, fracZ);
        return interpolate(i1, i2, fracZ * fracZ);
    }
    private float interpolate(float a, float b, float blend) {
        double theta = blend * Math.PI;
        float f = (float)(1F - Math.cos(theta)) * 0.5F;
        return a * (1F - f) + b * f;
    }
    private float getSmoothNoise(int x, int z) {
        float corners = (getNoise(x - 1, z - 1) + getNoise(x + 1, z + 1) + getNoise(x - 1, z + 1) + getNoise(x + 1, z - 1)) / 16F;
        float edges = (getNoise(x, z - 1) + getNoise(x + 1, z) + getNoise(x - 1, z) + getNoise(x, z + 1)) / 8F;
        float center = getNoise(x, z) / 4F;

        return corners + edges + center;
    }
    private float getNoise(int x, int z) {
        Random r = new Random(x * 49631 + z * 3151);
        return r.nextFloat() * 2F - 1F;
    }
}
