package com.jadencode.main.constants;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by gtrpl on 6/18/2016.
 */
public final class Icons {
    private static final HashMap<String, BufferedImage> ICONS = new HashMap<>();

    public static void register(String name, BufferedImage icon) {
        ICONS.put(name, icon);
    }

    public static BufferedImage get(String name) {
        return ICONS.get(name);
    }
}