package com.jadencode.main.generate.item.base;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemPartType {
    private final String typeName;
    private final BufferedImage icon;
    private final List<String> jointNames;

    public ItemPartType(String s, BufferedImage i, List<String> joints) {
        this.typeName = s;
        this.icon = i;
        this.jointNames = joints;
    }
    public String getTypeName() {
        return typeName;
    }
    public BufferedImage getIcon() {
        return icon;
    }
    public List<String> getJointNames() {
        return jointNames;
    }
}
