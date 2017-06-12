package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Icons;
import com.jadencode.main.constants.Strings;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class IconLoader extends ContentManager {

    public IconLoader() {
        super(Strings.Loaders.ICONS, -2);
    }

    private static BufferedImage decodeToImage(String imageString) {
        try {
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void consume(String name, JsonObject obj) {
        String base64 = obj.get(Strings.JsonKey.BASE64).getAsString();
        BufferedImage image = decodeToImage(base64);
        Icons.register(name, image);
    }
}