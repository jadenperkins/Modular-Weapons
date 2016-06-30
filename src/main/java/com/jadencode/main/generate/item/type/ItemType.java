package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 6/24/2016.
 */
public abstract class ItemType<T extends Item> implements WeightedItem {
    private final String itemBaseName;
    private final StatSet statSet;
    private final float weight;
    private final ScriptItem script;
    private final BufferedImage icon;
    private final Color color;

    public ItemType(String name, float w, StatSet stats, BufferedImage icon, Color color, ScriptItem s) {
        this.itemBaseName = name;
        this.weight = w;
        this.statSet = stats;
        this.script = s;
        this.icon = icon;
        this.color = color;
    }
    public String getDisplayName(T instance) {
        return this.script.getDisplayName(instance, this.getDisplayFallback(instance));
    }
    public String getDisplayFallback(T instance) {
        return this.getItemBaseName();
    }

    public String getItemBaseName() {
        return itemBaseName;
    }
    public StatSet getStatSet() {
        return this.statSet;
    }
    public BufferedImage getIcon() {
        return this.icon;
    }
    public Color getColor() {
        return this.color;
    }
    public void drawItem(T instance, BufferedImage out, Graphics2D g2d, int xOff, int yOff) {
        if(this.getIcon() == null) {
            g2d.setColor(this.getColor() == null ? Color.BLACK : this.getColor());
            g2d.fillRect(0, 0, 64, 64);
        } else {
            if(this.color == null) {
                g2d.drawImage(this.getIcon(), xOff, yOff, Color.WHITE, null);
            } else {
                for(int x = 0; x < this.getIcon().getWidth(); x++) {
                    for(int y = 0; y < this.getIcon().getHeight(); y++) {
                        if(this.getIcon().getRGB(x, y) >> 24 != 0x00) {
                            out.setRGB(x + xOff, y + yOff, this.getColor().getRGB());
                        }
                    }
                }
            }
        }
    }
    public void drawItem(T instance, BufferedImage out, Graphics2D g2d) {
        this.drawItem(instance, out, g2d, 0, 0);
    }
    public List<String> getItemCardStrings(T instance) {
        List<String> strings = new ArrayList<>();
        StatSet stats = instance.getStatSet();
        strings.addAll(stats.getStatsRaw().keySet().stream().map(statBase -> String.format("%s: %.2f", statBase.getStatName(), stats.get(statBase))).collect(Collectors.toList()));
        return strings;
    }
    public void printItemCard(T instance) {
        File dir = new File("pictures");
        File out = new File(dir, instance.getDisplayName().replace(" ", "_") + ".png");
        try {
            out.mkdirs();
            out.createNewFile();
            int width = 128;//this.getIcon() == null ? 64 : this.getIcon().getWidth();
            int height = 128;//this.getIcon() == null ? 64 : this.getIcon().getHeight();
            BufferedImage image = new BufferedImage(width + 64, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = image.createGraphics();

            this.drawItem(instance, image, g2d);

            g2d.setPaint(Color.WHITE);
            g2d.fillRect(width, 0, 64, image.getHeight());
            BufferedImage after = new BufferedImage(image.getWidth() * 8, image.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(8, 8);
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            after = scaleOp.filter(image, after);
            image = after;
            g2d = image.createGraphics();

            g2d.setPaint(instance.getQualityLevel().getColor());
            g2d.setFont(new Font("Helvetica", Font.BOLD, 14));
            int i = 2;
            String name;
            if(instance.getLevel() == 0) {
                name = instance.getDisplayName();
            } else {
                name = String.format("%s (%d)", instance.getDisplayName(), instance.getLevel());
            }


            g2d.drawString(name, width * 8 + 8, g2d.getFontMetrics().getHeight() * i);

            i += 1;
            for(String s : this.getDisplayInfo(instance)) {
                g2d.drawString("    " + s, width * 8 + 8, g2d.getFontMetrics().getHeight() * i);
                i++;
            }

            i += 1;
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
            List<String> strings = this.getItemCardStrings(instance);
            for (String string : strings) {
                g2d.drawString(string, width * 9, g2d.getFontMetrics().getHeight() * i);
                i++;
            }

            g2d.dispose();
            ImageIO.write(image, "PNG", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (WeaponPartInstance part : item.getPartsList()) {
//            if(part.getWeaponPart().getIcon().getWidth() > width) {
//                width = part.getWeaponPart().getIcon().getWidth();
//            }
//            if(part.getWeaponPart().getIcon().getHeight() > height) {
//                height = part.getWeaponPart().getIcon().getHeight();
//            }
//            if(part.getWeaponPart().getType().getIcon() == null) {
//                standard = false;
//            }
//        }
//        File dir = new File("pictures");
//        File out = new File(dir, item.getDisplayName().replace(" ", "_") + ".png");
//
//        try {
//            out.mkdirs();
//            out.createNewFile();
//            BufferedImage image = new BufferedImage(width + 64, height, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2d = image.createGraphics();
//            if(!standard) {
//                for (int x = 0; x < item.getPartsList().size(); x++) {
//                    Color c = item.getPartsList().get(x).getColor();
//                    c = c == null ? Color.WHITE : c;
//                    g2d.setColor(c);
//                    g2d.drawRect(0, x * 16, 16, 16);
//                }
//            } else {
//                for(WeaponPartInstance part : item.getPartsList()) {
//                    BufferedImage icon = part.getWeaponPart().getIcon();
//                    Color c = part.getColor();
//                    if(c == null) {
//                        g2d.drawImage(icon, 0, 0, null);
//                    } else {
//                        for(int x = 0; x < icon.getWidth(); x++) {
//                            for(int y = 0; y < icon.getHeight(); y++) {
//                                if(icon.getRGB(x, y) >> 24 != 0x00) {
//                                    image.setRGB(x, y, part.getColor().getRGB());
//                                }
//                            }
//                        }
//                    }
//                }
//                g2d.setPaint(Color.WHITE);
//                g2d.fillRect(width, 0, 64, image.getHeight());
//
//                BufferedImage after = new BufferedImage(image.getWidth() * 8, image.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
//                AffineTransform at = new AffineTransform();
//                at.scale(8, 8);
//                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//                after = scaleOp.filter(image, after);
//                image = after;
//
//                g2d = image.createGraphics();
//
//                g2d.setPaint(item.getQualityLevel().getColor());
//                g2d.setFont(new Font("Helvetica", Font.BOLD, 14));
//                int i = 2;
//                g2d.drawString(String.format("%s (%d)", item.getDisplayName(), item.getLevel()), width * 8 + 8, g2d.getFontMetrics().getHeight() * i);
//
//                i += 2;
//                g2d.setPaint(Color.DARK_GRAY);
//                g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
//                for (WeaponPartInstance weaponPartInstance : item.getPartsList()) {
//                    g2d.drawString(String.format("        %s", weaponPartInstance.getPartInfo(), weaponPartInstance.getLevel()), width * 9, g2d.getFontMetrics().getHeight() * i);
//                    i++;
//                    StatSet stats = weaponPartInstance.getStats();
//                    for (StatBase statBase : stats.getStatsRaw().keySet()) {
//                        g2d.drawString(String.format("                %s: %.2f", statBase.getStatName(), stats.get(statBase)), width * 9, g2d.getFontMetrics().getHeight() * i);
//                        i++;
//                    }
//                }
//
//                i += 1;
//                g2d.setPaint(Color.BLUE);
//                StatSet stats = item.getStatSet();
//                for (StatBase statBase : stats.getStatsRaw().keySet()) {
//                    g2d.drawString(String.format("%s: %.2f", statBase.getStatName(), stats.get(statBase)), width * 9, g2d.getFontMetrics().getHeight() * i);
//                    i++;
//                }
//
//                g2d.dispose();
//
//            }
//            ImageIO.write(image, "PNG", out);
//            System.out.println("Images stored in " + dir.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public float getWeight() {
        return this.weight;
    }

    public abstract T create(Random r, int level);
    public abstract T scaled(T original, int i);
    public abstract List<String> getDisplayInfo(T instance);
    public abstract QualityLevel getQualityLevel(T instance);
}