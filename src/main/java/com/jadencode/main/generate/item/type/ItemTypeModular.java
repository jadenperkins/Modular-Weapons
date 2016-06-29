package com.jadencode.main.generate.item.type;

import com.jadencode.main.constants.ItemParts;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.Joint;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.instance.ItemModular;
import com.jadencode.main.generate.item.instance.ItemPart;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedRandomFloat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        for(ItemPartType type : this.itemPartTypes) {
            List<ItemTypePart> parts = ItemParts.getPartsList(type);
            ItemTypePart part = WeightedRandomFloat.getRandomItem(r, parts);
            ItemPart instance = part.create(r, level);
            partInstances.add(instance);
        }
        return new ItemModular(this, partInstances);
    }
    @Override
    public String getDisplayFallback(ItemModular instance) {
        String s = instance.getPart(instance.getItemType().getPrimaryPartType()).getItemType().getMaterialName();
        for (ItemPartType type : instance.getItemType().getItemPartTypes()) {
            s = s + " " + instance.getPart(type).getItemType().getNameMod();
        }
        return s.trim();
    }
    @Override
    public void drawItem(ItemModular instance, BufferedImage out, Graphics2D g2d) {
        int width = 0;
        int height = 0;
        for (ItemPart itemPart : instance.getPartsList()) {
            if(itemPart.getItemType().getIcon().getWidth() > width) {
                width = itemPart.getItemType().getIcon().getWidth();
            }
            if(itemPart.getItemType().getIcon().getHeight() > height) {
                width = itemPart.getItemType().getIcon().getHeight();
            }
//            itemPart.getItemType().drawItem(itemPart, out, g2d);
        }
        ItemPart anchor = instance.getPart(this.anchorPart);
        List<ItemPart> parts = new ArrayList<>(instance.getPartsList());
        parts.remove(anchor);
        if(parts.size() > 0) {
            this.drawPart(anchor, 0, parts, out, g2d, width, height);
        } else {
        }
    }
    private void drawPart(ItemPart anchor, int index, List<ItemPart> subParts, BufferedImage out, Graphics2D g2d, int xOff, int yOff) {
        anchor.getItemType().drawItem(anchor, out, g2d, xOff, yOff);

        //We draw the anchor part first
        //Identify the next part in the list, and check all matching joints
        if(subParts.size() == 0) return;

        ItemPart next = subParts.get(index);
        List<Joint> jointsInAnchor = anchor.getItemType().getJoints();
        List<Joint> jointsInSub = next.getItemType().getJoints();
        List<String> matchingJoints = new ArrayList<>();

        for (Joint anchorJoint : jointsInAnchor) {
            for (Joint subJoint : jointsInSub) {
                if(anchorJoint.getName().equals(subJoint.getName())) {
                    matchingJoints.add(anchorJoint.getName());
                }
            }
        }
        if(matchingJoints.isEmpty()) {
            this.drawPart(anchor, index + 1, subParts, out, g2d, xOff, yOff);
        }
        System.out.println(String.format("%s and %s have common joints: %s", anchor.getDisplayName(), next.getDisplayName(), matchingJoints));

//        for(Joint j : anchorJoints) {
//            for(ItemPart itemPart : subParts) {
//                if(itemPart != anchor) {
//                    List<Joint> subJoints = itemPart.getItemType().getJoints();
//                    for(Joint s : subJoints) {
//                        if(j.getName().equals(s.getName())) {
//                            double x = j.getX() - s.getX();
//                            double y = j.getY() - s.getY();
//                            List<ItemPart> parts = new ArrayList<>(subParts);
//                            parts.remove(itemPart);
//                            this.drawPart(itemPart, parts, out, g2d, xOff + (int)x, yOff + (int)y);
//                        }
//                    }
//                }
//            }
//        }
    }
    @Override
    public List<String> getItemCardStrings(ItemModular instance) {
        List<String> ret = new ArrayList<>();
        for (ItemPart itemPart : instance.getPartsList()) {
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
        return new ItemModular(this, parts);
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