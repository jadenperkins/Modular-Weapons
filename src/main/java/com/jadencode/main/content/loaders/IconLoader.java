package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Icons;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class IconLoader extends ContentManager {
    public IconLoader() {
        super("Icons", -2);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        String base64 = obj.get("base64").getAsString();
        BufferedImage image = decodeToImage(base64);
        Icons.register(name, image);
    }
    private static BufferedImage decodeToImage(String imageString) {
        try {
            byte[] imageBytes = Base64.decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    private static BufferedImage getResizedCanvas(BufferedImage in) {
//        int w = 16 * in.getWidth();
//        int h = 16 * in.getHeight();
//        BufferedImage out = new BufferedImage(w, h, in.getType());
//        Graphics2D g = out.createGraphics();
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, w, h);
//        for (int i = 0; i < in.getWidth(); i++) {
//            for (int j = 0; j < in.getHeight(); j++) {
//                out.setRGB(i, j, in.getRGB(i, j));
//            }
//        }
//        return out;
//    }
}