package com.jadencode.main.generate.item.type;

import com.jadencode.main.constants.ItemParts;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.Joint;
import com.jadencode.main.generate.item.Node;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.instance.ItemModular;
import com.jadencode.main.generate.item.instance.ItemPart;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedRandomFloat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypeModular extends ItemType<ItemModular> {
    private final ItemPartType primaryPartType;
    private final ItemPartType anchorPart;
    private final List<ItemPartType> itemPartTypes;
    private final List<ItemPartType> optionalParts;

    public ItemTypeModular(String name, float w, StatSet stats, ItemPartType primary, ItemPartType anchor, List<ItemPartType> types, List<ItemPartType> optional, ScriptItem s) {
        super(name, w, stats, primary.getIcon(), Color.WHITE, s);
        this.primaryPartType = primary;
        this.anchorPart = anchor;
        this.itemPartTypes = types;
        this.optionalParts = optional;
    }
    private static BufferedImage trim(BufferedImage image) {
        int l, r, t, b;
        l = r = t = b = 0;

        for (int i = 0; i < image.getWidth() && isColumnTrans(image, i); i++) {
//            if (!isColumnTrans(image, i)) {
//                break;
//            } else {
                l = i;
//            }
        }
        for (int i = image.getWidth() - 1; i >= 0 && isColumnTrans(image, i); i--) {
//            if (!isColumnTrans(image, i)) {
//                break;
//            } else {
                r = i;
//            }
        }

        for (int j = 0; j < image.getHeight() && isRowTrans(image, j); j++) {
//            if (!isRowTrans(image, j)) {
//                break;
//            } else {
                t = j;
//            }
        }
        for (int j = image.getHeight() - 1; j >= 0 && isRowTrans(image, j); j--) {
//            if (!isRowTrans(image, j)) {
//                break;
//            } else {
                b = j;
//            }
        }
        return image.getSubimage(l, t, r - l + 1, b - t + 1);
    }
    private static boolean isColumnTrans(BufferedImage image, int i) {
        for (int j = 0; j < image.getHeight(); j++) {
            if(image.getRGB(i, j) >> 24 != 0x00) return false;
        }
        return true;
    }
    private static boolean isRowTrans(BufferedImage image, int j) {
        for (int i = 0; i < image.getWidth(); i++) {
            if(image.getRGB(i, j) >> 24 != 0x00) return false;
        }
        return true;
    }

    public ItemPartType getPrimaryPartType() {
        return primaryPartType;
    }

    public ItemPartType getAnchorPart() {
        return anchorPart;
    }

    public List<ItemPartType> getItemPartTypes() {
        return this.itemPartTypes;
    }

    public List<ItemPartType> getOptionalParts() {
        return optionalParts;
    }

    @Override
    public ItemModular create(Random r, int level) {
        List<ItemPart> partInstances = new ArrayList<>();

        for (ItemPartType type : this.itemPartTypes) {
            List<ItemTypePart> parts = ItemParts.getPartsList(type);
            ItemTypePart part = WeightedRandomFloat.getRandomItem(r, parts);
            ItemPart instance = part.create(r, level);
            partInstances.add(instance);
        }
        StatSet stats = this.getStatSet().scaled(level).combine(partInstances.stream().map(ItemPart::getStatSet).collect(Collectors.toList()));
        return new ItemModular(this, partInstances, stats);
    }

    @Override
    public String getDisplayFallback(ItemModular instance) {
        StringBuilder s = new StringBuilder(instance.getPart(instance.getItemType().getPrimaryPartType()).getItemType().getMaterialName());
        List<ItemPartType> partTypes = instance.getItemType().getItemPartTypes();
        partTypes.forEach(type -> s.append(" ").append(instance.getPart(type).getItemType().getNameMod()));
        return s.toString().trim();
    }
    @Override
    public BufferedImage render(ItemModular instance) {
//        int width = 0;
//        int height = 0;
//        for (ItemPart itemPart : instance.getPartsList()) {
//            if (itemPart.getItemType().getIcon().getWidth() > width) {
//                width = itemPart.getItemType().getIcon().getWidth();
//            }
//            if (itemPart.getItemType().getIcon().getHeight() > height) {
//                height = itemPart.getItemType().getIcon().getHeight();
//            }
//        }
        List<ItemPart> parts = instance.getPartsList();
        int width = parts.stream().mapToInt(part -> part.getItemType().getIcon().getWidth()).max().orElse(0);
        int height = parts.stream().mapToInt(part -> part.getItemType().getIcon().getWidth()).max().orElse(0);

        ItemPart anchor = instance.getPart(this.anchorPart);

        Node<ItemPart> tree = Node.tree(anchor, instance.getPartsList(), (c, p) -> {
            for (Joint joint : c.getItemType().getJoints()) {
                for (Joint j : p.getItemType().getJoints()) {
                    if (joint.getName().equals(j.getName())) {
                        return true;
                    }
                }
            }
            return false;
        });
        BufferedImage untrimmed = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        this.drawTree(tree, untrimmed, untrimmed.createGraphics(), width * 2, height * 2);
        return trim(untrimmed);
    }

    private void drawTree(Node<ItemPart> anchor, BufferedImage out, Graphics2D g2d, int xOff, int yOff) {
        ItemPart item = anchor.getData();
        int x = xOff;
        int y = yOff;
        if (anchor.getParent() == null) {
            BufferedImage itemRender = item.getItemType().render(item);
            g2d.drawImage(itemRender, x, y, null);
        } else {
            ItemPart parent = anchor.getParent().getData();
            int joints = 0;
            for (Joint parentJoint : parent.getItemType().getJoints()) {
                for (Joint childJoint : item.getItemType().getJoints()) {
                    if (parentJoint.getName().equals(childJoint.getName()) && joints == 0) {
                        joints++;
                        x += (int) (parentJoint.getX() - childJoint.getX());
                        y += (int) (parentJoint.getY() - childJoint.getY());
                        BufferedImage itemRender = item.getItemType().render(item);
                        g2d.drawImage(itemRender, x, y, null);
                    }
                }
            }
        }
        List<Node<ItemPart>> children = anchor.getChildren();
        for (Node<ItemPart> childNode : children) {
            this.drawTree(childNode, out, g2d, x, y);
        }
    }

    @Override
    public List<String> getItemCardStrings(ItemModular instance) {
        List<String> ret = new ArrayList<>();
        List<ItemPart> parts = instance.getPartsList();
        for (ItemPart itemPart : parts) {
            ret.add(String.format("        %s (%s - %d)", itemPart.getDisplayName(), itemPart.getQualityLevel().getQualityName(), itemPart.getLevel()));
            List<String> part = itemPart.getItemType().getItemCardStrings(itemPart);
            part.forEach(s -> ret.add("                " + s));
        }
        ret.addAll(super.getItemCardStrings(instance));
        return ret;
    }

    @Override
    public ItemModular scaled(ItemModular original, int i) {
        List<ItemPart> parts = new ArrayList<>();
        original.getPartsList().forEach(p -> parts.add(p.getItemType().scaled(p, i)));
        StatSet stats = this.getStatSet().scaled(i).combine(parts.stream().map(ItemPart::getStatSet).collect(Collectors.toList()));

        return new ItemModular(this, parts, stats);
    }

    @Override
    public List<String> getDisplayInfo(ItemModular instance) {
        return new ArrayList<>();
    }

    @Override
    public QualityLevel getQualityLevel(ItemModular instance) {
        return QualityLevel.calculate(instance);
    }
}