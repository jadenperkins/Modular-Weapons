package com.main.generate.weapon;

import java.awt.image.BufferedImage;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class WeaponPartType {
    private final String typeName;
    private final BufferedImage icon;

    public WeaponPartType(String s, BufferedImage i) {
        this.typeName = s;
        this.icon = i;
    }
    public String getTypeName() {
        return typeName;
    }
    public BufferedImage getIcon() {
        return icon;
    }
}