package com.jadencode.main.renderengine.terrain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JPERKI8 on 7/25/2016.
 */
public class Biome {
    private static final List<Biome> biomes = new ArrayList<>();

    //temperature maps between -50 and 50 (C), which maps between 0 and 1: -50 = 0, 0 = 0.5, 50 = 1
    //rainfall maps between 0 and 500 (cm), which maps between 0 and 1: 0 = 0, 250 = 0.5, 500 = 1


    public static final Biome plateau = new Biome("Plateau", 0.9F, 0.1F, 0.3F, new Color(255, 100, 0)).setTerrain(1F, 0.1F, 1F);

    public static final Biome mountain = new Biome("Mountain", 0.1F, 0.1F, 0.9F, new Color(255, 255, 255)).setTerrain(0F, 1F, 2F);
    //t = -34 to 12, r = 15 to 25
    public static final Biome tundra = new Biome("Tundra", 0.39F, 0.04F, 0.2F, new Color(0, 255, 255));
    //t = -54 to 21, r = 30 to 84
    public static final Biome borealForest = new Biome("Boreal Forest", 0.335F, 0.114F, 0.3F, new Color(62, 116, 92)).setTerrain(0F, 0.1F, 1F);

    //t = -2 to 26, r = 15 to 26
    public static final Biome coldDesert = new Biome("Cold Desert", 0.62F, 0.041F, 0.1F, new Color(214, 204, 165)).setTerrain(0F, 0F, 1.2F);
    //t = -40 to 21, r = 25 to 76
    public static final Biome grassland = new Biome("Grassland", 0.405F, 0.101F, 0.1F, new Color(194, 144, 0)).setTerrain(0F, 0F, 0.2F);
    //t = -30 to 30, r = 76 to 152
    public static final Biome tempForest = new Biome("Temperate Forest", 0.5F, 0.228F, 0.2F, new Color(137, 255, 67)).setTerrain(0F, 0.1F, 0.2F);
    //t = 20 to 49, r = 0 to 15
    public static final Biome warmDesert = new Biome("Warm Desert", 0.845F, 0.015F, 0.1F, new Color(255, 0, 0)).setTerrain(0F, 0F, 1.2F);
    //t = 24 to 27, r = 51 to 127
    public static final Biome savanna = new Biome("Savanna", 0.755F, 0.178F, 0.1F, new Color(168, 116, 29)).setTerrain(0F, 0F, 0.2F);
    //t = 20 to 25, r = 70 to 200
    public static final Biome monsoonForest = new Biome("Monsoon Forest", 0.725F, 0.27F, 0.2F, new Color(159, 199, 174));

    public static final Biome rainforest = new Biome("Tropical Rainforest", 0.725F, 0.785F, 0.2F, new Color(0, 145, 0)).setTerrain(0F, 0.4F, 0.8F);

    private final String name;
    private final float temperature;
    private final float rainfall;
    private final float height;
    private final Color color;

    private float heightShift = 0F;
    private float roughness = 0F;
    private float intensity = 1F;

    public Biome(String n, float t, float r, float h, Color c) {
        this.name = n;
        this.temperature = t;
        this.rainfall = r;
        this.height = h;
        this.color = c;
        biomes.add(this);
    }
    public Biome setTerrain(float s, float r, float i) {
        this.heightShift = s;
        this.roughness = r;
        this.intensity = i;
        return this;
    }
    public float getTemperature() {
        return temperature;
    }
    public float getRainfall() {
        return rainfall;
    }
    public float getHeight() {
        return height;
    }
    public float getHeightShift() {
        return heightShift;
    }
    public float getRoughness() {
        return roughness;
    }
    public float getIntensity() {
        return intensity;
    }
    public Color getColor() {
        return color;
    }
    public float calculate(float t, float r, float h) {
        return (float)Math.sqrt(Math.pow(t - this.temperature, 2) + Math.pow(r - this.rainfall, 2) + Math.pow(h - this.height, 2));
    }

    public static List<Biome> getBiomes() {
        return biomes;
    }
    @Override
    public String toString() {
        return name;
    }
}
