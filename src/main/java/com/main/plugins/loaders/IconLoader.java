package com.main.plugins.loaders;

import com.main.constants.Icons;
import com.main.plugins.Plugin;
import com.main.plugins.pipeline.PipelineObjectIcon;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class IconLoader extends ContentManager<PipelineObjectIcon> {
    public IconLoader() {
        super("icons", -2, Plugin::getIcons);
    }
    @Override
    public void consume(PipelineObjectIcon object) {
        BufferedImage image = decodeToImage(object.getBase64());
        Icons.register(object.getName(), image);
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
}