package com.jadencode.main.constants;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Jaden on 1/22/2015.
 */
public final class Colors {
    private static final HashMap<String, Color> COLORS = new HashMap<>();

    public static void register(String name, Color color) {
        COLORS.put(name, color);
    }
    public static Color get(String name) {
        return COLORS.get(name);
    }
}
