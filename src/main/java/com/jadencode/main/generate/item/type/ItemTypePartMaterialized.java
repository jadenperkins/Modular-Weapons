package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.item.base.ItemPartBase;
import com.jadencode.main.material.Material;
import com.jadencode.main.scripts.ScriptItem;

import java.awt.*;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypePartMaterialized extends ItemTypePart {
    private final String       nameMod;
    private final String       partDescription;
    private final Color        partColor;
    private final String       materialName;

    public ItemTypePartMaterialized(ItemPartBase part, Material material, ScriptItem s) {
        super(material.getName() + " " + part.getPartName(), part.getWeight() * material.getWeight(), part.modifyStats(material), s, part.getIcon(), material.getQualityLevel(), part.getPartType());
        this.nameMod = part.getNameMod();
        this.partDescription = part.getPartInfo().replace("%s", material.getName());
        this.partColor = material.getColor();
        this.materialName = material.getName();
    }
    @Override
    public String getNameMod() {
        return this.nameMod;
    }
    @Override
    public String getPartInfo() {
        return this.partDescription;
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