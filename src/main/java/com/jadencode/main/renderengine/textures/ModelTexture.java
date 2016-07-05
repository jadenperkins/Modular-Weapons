package com.jadencode.main.renderengine.textures;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class ModelTexture {
    private final int textureID;
    private float shineDamper = 1;
    private float reflectivity = 0;
    private boolean hasTransparency = false;
    private boolean useFakeLighting = false;

    private int numberOfRows = 1;

    public ModelTexture(int id) {
        this.textureID = id;
    }
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getTextureID() {
        return textureID;
    }

    public boolean getHasTransparency() {
        return this.hasTransparency;
    }

    public void setHasTransparency(boolean val) {
        hasTransparency = val;
    }

    public boolean getUseFakeLighting() {
        return this.useFakeLighting;
    }

    public void setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}