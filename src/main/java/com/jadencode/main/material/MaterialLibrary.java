package com.jadencode.main.material;

import com.jadencode.main.StackMap;
import com.jadencode.main.material.libraries.GemLibrary;
import com.jadencode.main.material.libraries.MetalLibrary;
import com.jadencode.main.material.libraries.PlantLibrary;
import com.jadencode.main.material.libraries.WoodLibrary;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jaden on 2/10/2015.
 */
public abstract class MaterialLibrary {

    private static final Set<MaterialLibrary> libs = new HashSet<>();

    private static MaterialLibrary woodLibrary;
    private static MaterialLibrary metalLibrary;
    private static MaterialLibrary plantLibrary;
    private static MaterialLibrary gemLibrary;

    private HashMap<String, MaterialBase>                              materialBases     = new HashMap<>();
    private HashMap<String, MaterialModifier>                          materialModifiers = new HashMap<>();
    private HashMap<String, MaterialResource>                          materialResources = new HashMap<>();
    private StackMap<MaterialBase, MaterialModifier, MaterialResource> mappedMaterials   = new StackMap<>();

    private final String name;

    public String getName() {
        return this.name;
    }

    public MaterialLibrary(String name) {
        this.name = name;
        libs.add(this);
    }

    public HashMap<String, MaterialBase> getMaterialBases() {
        return this.materialBases;
    }

    public HashMap<String, MaterialModifier> getMaterialModifiers() {
        return this.materialModifiers;
    }

    public HashMap<String, MaterialResource> getMaterialResources() {
        return this.materialResources;
    }

    public static Set<MaterialLibrary> getLibraries() {
        return libs;
    }

    private static void loadAll() {
        for (MaterialLibrary lib : libs) {
            lib.load();
        }
    }

    protected void registerMaterialBase(MaterialBase base) {
        this.materialBases.put(base.getName(), base);
    }

    protected void registerMaterialModifier(MaterialModifier mod) {
        this.materialModifiers.put(mod.getName(), mod);
    }

    protected void registerResource(MaterialResource resource) {
        this.materialResources.put(resource.getName(), resource);
        this.mappedMaterials.put(resource.getMaterialBase(), resource.getMaterialModifier(), resource);
    }
    public MaterialBase getMaterialBase(String name) {
        return this.materialBases.get(name);
    }
    public MaterialModifier getMaterialModifier(String name) {
        return this.materialModifiers.get(name);
    }
    public MaterialResource getResource(String name) {
        return this.materialResources.get(name);
    }
    public MaterialResource getResource(MaterialBase base, MaterialModifier mod) {
        return this.mappedMaterials.get(base, mod);
    }

    protected final void load() {

        this.loadMaterials();
        for(MaterialBase base : this.materialBases.values()) {
            for(MaterialModifier mod : this.materialModifiers.values()) {

                String name = (mod.getName() + " " + base.getName()).trim();
                Color color = combineColors(base.getColor(), mod.getColor());
                float weight = base.getWeight() * mod.getWeight();
                float multiplier = base.getMultiplier() * mod.getMultiplier();
                float level = (float) Math.ceil(base.getLevel() * mod.getLevel());

                MaterialResource resource = new MaterialResource(this, base, mod, name, color, weight, multiplier, level);
                this.registerResource(resource);
            }
        }
        System.out.println(String.format("Initialized %d new %s resources!", this.materialResources.size(), this.getName()));
    }
    public abstract void loadMaterials();

    public static void init() {

        metalLibrary = MetalLibrary.getInstance();
        woodLibrary = WoodLibrary.getInstance();
        plantLibrary = PlantLibrary.getInstance();
        gemLibrary = GemLibrary.getInstance();

        loadAll();
    }
    private static Color combineColors(Color c1, Color c2) {

        if(c2 == null) {
            return c1;
        }

        int r1 = c1.getRed();
        int g1 = c1.getGreen();
        int b1 = c1.getBlue();

        int r2 = c2.getRed();
        int g2 = c2.getGreen();
        int b2 = c2.getBlue();

        int newR = combine(r1, r2);
        int newG = combine(g1, g2);
        int newB = combine(b1, b2);

        return new Color(newR, newG, newB);
    }
    private static int combine(int c1, int c2) {
        int mod = 0;

        for(int i = 0; i < COMPONENT_1_TIMES; i++) {
            mod += c1;
        }
        for(int i = 0; i < COMPONENT_2_TIMES; i++) {
            mod += c2;
        }
        int ret = (int)Math.ceil(Math.min(255, (double)mod / (double)(COMPONENT_TOTAL)));

//        int ret = (int)Math.ceil(Math.min(255, Math.pow((double)(c1+1)*(double)(c1+1)*(double)(c1+1)*(double)(c2+1), 1D/4D)));
        return ret;
    }
    private static final int COMPONENT_1_TIMES = 3;
    private static final int COMPONENT_2_TIMES = 1;
    private static final int COMPONENT_TOTAL = COMPONENT_1_TIMES + COMPONENT_2_TIMES;

    public static MaterialLibrary getWoodLibrary() {
        return woodLibrary;
    }
    public static MaterialLibrary getMetalLibrary() { return metalLibrary; }
    public static MaterialLibrary getPlantLibrary() { return plantLibrary; }
    public static MaterialLibrary getGemLibrary() {
        return gemLibrary;
    }
}