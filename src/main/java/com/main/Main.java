package com.main;

import com.main.generate.weapon.WeaponGenerator;
import com.main.generate.weapon.WeaponInstance;
import com.main.generate.weapon.WeaponPartInstance;
import com.main.plugins.ContentLoader;
import com.main.stat.StatBase;
import com.main.stat.StatSet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jaden on 1/19/2015.
 */
public class Main {
    public static final int LEVEL_CAP = 100;
    public static final World theWorld   = new World();

    public static void main(String[] args) {
        new ContentLoader().load();

        int weaponLevel = theWorld.getRandom().nextInt(50) + 1;
        WeaponInstance weap = new WeaponGenerator().generate(theWorld.getRandom(), weaponLevel);

        System.out.println("Created " + weap.getDisplayName() + " with quality " + weap.getQualityLevel().getQualityName());
        printWeapon(weap);
    }

    private static boolean isStandard(WeaponInstance weap) {
        List<WeaponPartInstance> partsList = weap.getPartsList();
        for (WeaponPartInstance part : partsList) {
            if (part.getWeaponPart().getType().getIcon() == null) return false;
        }
        return true;
    }
    private static void printWeapon(WeaponInstance weap) {
        List<WeaponPartInstance> partsList = weap.getPartsList();
        boolean standard = isStandard(weap);

        File dir = new File("pictures");
        File out = new File(dir, weap.getDisplayName().replace(" ", "_") + ".png");

        try {
            out.mkdirs();
            out.createNewFile();
            BufferedImage image = new BufferedImage(64, partsList.size() * 16, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            if(!standard) {
                for (int x = 0; x < partsList.size(); x++) {
                    Color c = partsList.get(x).getColor();
                    c = c == null ? Color.WHITE : c;
                    g2d.setColor(c);
                    g2d.drawRect(0, x * 16, 16, 16);
                }
            } else {
                for(WeaponPartInstance part : partsList) {
                    BufferedImage icon = part.getWeaponPart().getIcon();
                    Color c = part.getColor();
                    if(c == null) g2d.drawImage(icon, 0, 0, null);
                    else {
                        int width = icon.getWidth();
                        int height = icon.getHeight();
                        for(int x = 0; x < width; x++) {
                            for(int y = 0; y < height; y++) {
                                if(icon.getRGB(x, y) >> 24 != 0x00) image.setRGB(x, y, part.getColor().getRGB());
                            }
                        }
                    }
                }
                g2d.setPaint(Color.WHITE);
                g2d.fillRect(16, 0, 48, image.getHeight());

                BufferedImage after = new BufferedImage(image.getWidth() * 8, image.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
                AffineTransform at = new AffineTransform();
                at.scale(8, 8);
                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                after = scaleOp.filter(image, after);
                image = after;

                g2d = image.createGraphics();

                g2d.setPaint(weap.getQualityLevel().getColor());
                g2d.setFont(new Font("Helvetica", Font.BOLD, 14));
                int i = 2;
                g2d.drawString(String.format("%s (%d)", weap.getDisplayName(), weap.getLevel()), 16 * 8 + 8, g2d.getFontMetrics().getHeight() * i);

                i += 2;
                g2d.setPaint(Color.DARK_GRAY);
                g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
                for (WeaponPartInstance weaponPartInstance : partsList) {
                    g2d.drawString(String.format("        %s", weaponPartInstance.getPartInfo(), weaponPartInstance.getLevel()), 16 * 9, g2d.getFontMetrics().getHeight() * i);
                    i++;
                    StatSet stats = weaponPartInstance.getStats();
                    for (StatBase statBase : stats.getStatsRaw().keySet()) {
                        g2d.drawString(String.format("                %s: %.2f", statBase.getStatName(), stats.get(statBase)), 16 * 9, g2d.getFontMetrics().getHeight() * i);
                        i++;
                    }
                }

                i += 1;
                g2d.setPaint(Color.BLUE);
                StatSet stats = weap.getStatSet();
                for (StatBase statBase : stats.getStatsRaw().keySet()) {
                    g2d.drawString(String.format("%s: %.2f", statBase.getStatName(), stats.get(statBase)), 16 * 9, g2d.getFontMetrics().getHeight() * i);
                    i++;
                }

                g2d.dispose();

            }
            ImageIO.write(image, "PNG", out);
            System.out.println("Images stored in " + dir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
