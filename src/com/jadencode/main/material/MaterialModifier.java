package com.upadvisor.main.material;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialModifier extends MaterialAttribute {

    private final boolean isNone;

    public MaterialModifier(MaterialLibrary lib, String name, Color color, float weight, float mod, float level) {
        super(name, color, weight, mod, level);
        if(lib.getMaterialModifier(name) != null) {
            throw new IllegalArgumentException(name + " is already in use!");
        }
        this.isNone = name.isEmpty();
        lib.registerMaterialModifier(this);
    }
    public MaterialModifier(MaterialLibrary lib, String name, int color, float weight, float mod, float level) {
        this(lib, name, new Color(color), weight, mod, level);
    }
    public MaterialModifier(MaterialLibrary lib, String name, Color color, float weight, float mod) {
        this(lib, name, color, weight, mod, 1F);
    }
    public MaterialModifier(MaterialLibrary lib, String name, int color, float weight, float mod) {
        this(lib, name, new Color(color), weight, mod, 1F);
    }
    public boolean isNone() {
        return this.isNone;
    }
}