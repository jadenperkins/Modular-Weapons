package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.PartTypes;
import com.jadencode.main.generate.weapon.WeaponPartType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class PartTypeLoader extends ContentManager {
    public PartTypeLoader() {
        super("Part Types", 1);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        BufferedImage image = null;
        if(obj.has("icon")) {
            File f = new File("plugins/icons/" + obj.get("icon").getAsString() + ".png");
            try {
                image = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PartTypes.register(new WeaponPartType(name, image));
    }
}