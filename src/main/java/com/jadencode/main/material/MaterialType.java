package com.jadencode.main.material;

import com.jadencode.main.StackMap;
import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.material.libraries.GemLibrary;
import com.jadencode.main.material.libraries.MetalLibrary;
import com.jadencode.main.material.libraries.PlantLibrary;
import com.jadencode.main.material.libraries.WoodLibrary;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Jaden on 2/10/2015.
 */
public class MaterialType {
    private final String name;

    public MaterialType(String name) {
        this.name = name;
        MaterialTypes.register(this);
    }
    public String getName() {
        return this.name;
    }
}