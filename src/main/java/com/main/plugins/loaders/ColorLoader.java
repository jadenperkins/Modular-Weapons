package com.main.plugins.loaders;

import com.main.constants.Colors;
import com.main.plugins.Plugin;
import com.main.plugins.pipeline.PipelineObjectColor;

import java.awt.*;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ColorLoader extends ContentManager<PipelineObjectColor> {
    public ColorLoader() {
        super("colors", 2, Plugin::getColors);
    }
    @Override
    public void consume(PipelineObjectColor color) {
        Color c = new Color(color.getRed(), color.getGreen(), color.getBlue());
        Colors.register(color.getName(), c);
    }
}