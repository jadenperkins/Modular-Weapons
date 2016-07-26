package com.jadencode.main.renderengine.terrain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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

    private final MapGenerator temperatureGenerator;
    private final MapGenerator heightGenerator;
    private final MapGenerator rainfallGenerator;
    private final int terrainSize;

    public HeightsGenerator(int gridX, int gridZ, int vertexCount, long seed) {
        this.seed = seed;
        this.xOffset = gridX * (vertexCount - 1);
        this.zOffset = gridZ * (vertexCount - 1);
        this.RNG = new Random(seed);
        this.terrainSize = vertexCount;

        this.temperatureGenerator = new MapGenerator(20, this.seed + "temperature".hashCode(), 2, this.terrainSize).setColor(1, 0, 0);
        this.heightGenerator = new MapGenerator(20, this.seed + "heights".hashCode(), 2, this.terrainSize).setColor(0, 1, 0);
        this.rainfallGenerator = new MapGenerator(20, this.seed + "rainfall".hashCode(), 2, this.terrainSize).setColor(0, 0, 1);
    }
    public float[][] generateHeights() {
        System.out.println("Generating height map");
        float[][] height = heightGenerator.generateMap();
        System.out.println("Generating rainfall map");
        float[][] rainfall = rainfallGenerator.generateMap();
        System.out.println("Generating temperature map");
        float[][] temperature = temperatureGenerator.generateMap();

        System.out.println("Generating biome map");
        Biome[][] biomeMap = new Biome[terrainSize][terrainSize];
        BufferedImage image = new BufferedImage(terrainSize, terrainSize, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < terrainSize; i++) {
            for (int j = 0; j < terrainSize; j++) {
                float h = height[i][j];
                float t = temperature[i][j];
                float r = rainfall[i][j];

                List<Biome> biomes = new ArrayList<>(Biome.getBiomes());
                biomes.sort((b1, b2) -> Float.compare(b1.calculate(t, r, h), b2.calculate(t, r, h)));
                biomeMap[i][j] = biomes.get(0);
                image.setRGB(i, j, biomes.get(0).getColor().getRGB());
            }
        }
        float[][] newHeight = heightGenerator.normalize(heightGenerator.modify(height, biomeMap));
        return newHeight;
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
