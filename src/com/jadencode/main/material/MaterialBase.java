package com.upadvisor.main.material;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialBase extends MaterialAttribute {

    public MaterialBase(MaterialLibrary library, String name, Color color, float weight, float mod, float level) {
        super(name, color, weight, mod, level);
//        if(library.getMaterialBase(name) != null) {
//            throw new IllegalArgumentException(name + " is already in use!");
//        }
        library.registerMaterialBase(this);
    }
    public MaterialBase(MaterialLibrary library, String name, int color, float weight, float mod, float level) {
        this(library, name, new Color(color), weight, mod, level);
    }
    public MaterialBase(MaterialLibrary library, String name, int color, float weight, int level) {
        super(name, new Color(color), weight, 1F, level);
        library.registerMaterialBase(this);
    }
    public MaterialBase(MaterialLibrary library, String name, Color color, float weight) {
        this(library, name, color, weight, 1F, 1);
    }
}