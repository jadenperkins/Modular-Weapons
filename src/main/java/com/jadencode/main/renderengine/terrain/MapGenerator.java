package com.jadencode.main.renderengine.terrain;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by JPERKI8 on 7/25/2016.
 */
public class MapGenerator {
    private final int scale;
    private final Random rand;
    private final long seed;
    private final int smoothness;
    private final int size;
    private int red = 1;
    private int green = 1;
    private int blue = 1;
    private float vertical = 0F;
    private float horizontal = 0F;

    public MapGenerator(int s, long l, int m, int size) {
        this.scale = s;
        this.seed = l;
        this.smoothness = m;
        this.size = size;
        this.rand = new Random(l);
    }
    public MapGenerator setVertical(float f) {
        this.vertical = f;
        return this;
    }
    public MapGenerator setHorizontal(float f) {
        this.horizontal = f;
        return this;
    }
    public MapGenerator setColor(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;

        return this;
    }
    public BufferedImage asImage(float[][] map) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int c = (int)Math.min(Math.abs(255F * map[i][j]), 255);
                image.setRGB(i, j, new Color(c * this.red, c * this.green, c * this.blue).getRGB());
            }
        }
        return image;
    }
    public float[][] normalize(float[][] original) {
        float min = 1;
        float max = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                float value = original[i][j];
                if(value < min) min = value;
                if(value > max) max = value;
            }
        }

        float[][] out = new float[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                out[i][j] = (original[i][j] - min) / (max - min);
            }
        }
        return out;
    }
    public float[][] modify(float[][] original, Biome[][] biomes) {
        float[][] out = new float[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Biome biome = biomes[i][j];
                out[i][j] = original[i][j] * biome.getIntensity() + biome.getHeightShift() + this.rand.nextFloat() * biome.getRoughness() * 0.05F;
            }
        }
        return out;
    }
    public float[][] generateMap() {
        float[][] noiseArray = new float[size][size];
        for(int j = 0; j < size; j++) {
            for(int i = 0; i < size; i++) {
                float noise = this.rand.nextFloat() + this.vertical * (float)j / (float)size + this.horizontal * (float) i / (float)size;
                noiseArray[i][j] = noise;
            }
        }
        float[][] smoothed = smooth(noiseArray);
        for(int i = 0; i < this.smoothness; i++) {
            smoothed = smooth(smoothed);
        }
        float[][] out = this.normalize(smoothed);
        return out;
    }
    private float[][] smooth(float[][] noiseArray) {
        float[][] smoothed = new float[size][size];
        for(int j = 0; j < size; j++) {
            for(int i = 0; i < size; i++) {
                smoothed[i][j] = getMean(noiseArray, i, j);
            }
        }
        return smoothed;
    }
    private float getMean(float[][] noiseArray, int x, int z) {
        float ret = 0;
        for(int l = 0; l <= scale; l++) {
            float divisor = (float)Math.pow(2, l + 1);
            int loop = scale - l;

            float sum = 0;
            int summed = 0;
            for(int i = -loop; i <= loop; i++) {
                for(int j = -loop; j <= loop; j++) {
                    int dx = x + i;
                    int dz = z + j;
                    if(dx >= 0 && dx < size && dz >= 0 && dz < size) {
                        sum += noiseArray[dx][dz];
                        summed++;
                    }
                }
            }
            ret += (sum / (float)Math.max(1, summed)) / divisor;
        }
        return ret;
    }
}
