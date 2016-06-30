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
    /**Render an item instance to and return a BufferedImage*/
    public BufferedImage render(T instance) {
        if(this.getIcon() == null) {
            BufferedImage ret = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = ret.createGraphics();
            g2d.setPaint(this.getColor() == null ? Color.BLACK : this.getColor());
            g2d.fillRect(0, 0, 64, 64);
            return ret;
        }
        BufferedImage ret = new BufferedImage(this.getIcon().getWidth(), this.getIcon().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = ret.createGraphics();
        if(this.getColor() == null) {
            g2d.drawImage(this.getIcon(), 0, 0, Color.WHITE, null);
        } else {
            for(int x = 0; x < this.getIcon().getWidth(); x++) {
                for(int y = 0; y < this.getIcon().getHeight(); y++) {
                    if(this.getIcon().getRGB(x, y) >> 24 != 0x00) {
                        ret.setRGB(x, y, this.getColor().getRGB());
                    }
                }
            }
        }
        return ret;
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
            BufferedImage renderedItem = this.render(instance);
            int width = renderedItem.getWidth();
            int height = Math.max(renderedItem.getHeight(), 40);

//            BufferedImage image = new BufferedImage(width + 64, height, BufferedImage.TYPE_INT_ARGB);

            BufferedImage itemCard = new BufferedImage(width + 64, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = itemCard.createGraphics();
            g2d.drawImage(renderedItem, 0, 0, null);
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(width, 0, 64, height);

            BufferedImage after = new BufferedImage(itemCard.getWidth() * 8, itemCard.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(8, 8);
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            after = scaleOp.filter(itemCard, after);

            itemCard = after;
            g2d = itemCard.createGraphics();

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
            ImageIO.write(itemCard, "PNG", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
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