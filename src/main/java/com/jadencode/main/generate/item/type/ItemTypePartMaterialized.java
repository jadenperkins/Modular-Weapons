package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.item.base.ItemPartBase;
import com.jadencode.main.generate.item.instance.ItemPart;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.scripts.ScriptItem;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypePartMaterialized extends ItemTypePart {
    private final String       nameMod;
    private final List<String> partDescription;
    private final Color        partColor;
    private final String       materialName;

    public ItemTypePartMaterialized(ItemPartBase part, Material material) {
        super(material.getName() + " " + part.getPartName(), part.getWeight() * material.getWeight(), part.modifyStats(material), part.getScript(), part.getIcon(), material.getQualityLevel(), part.getPartType());
        this.nameMod = part.getNameMod();
        if(part.getPartInfo().equals(""))
            this.partDescription = Arrays.asList(String.format("%s crafted from %s", part.getPartName(), material.getName()));
        else
            this.partDescription = Arrays.asList(part.getPartInfo().replace("%s", material.getName()));
        this.partColor = material.getColor();
        this.materialName = material.getName();
    }
    @Override
    public List<String> getDisplayInfo(ItemPart instance) {
        return this.partDescription;
    }
    @Override
    public String getNameMod() {
        return this.nameMod;
    }
    @Override
    public Color getColor() {
        return this.partColor;
    }
    @Override
    public String getMaterialName() {
        return this.materialName;
    }
}