package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.base.ItemMaterializedBase;
import com.jadencode.main.generate.item.instance.ItemMaterialized;
import com.jadencode.main.material.Material;
import com.jadencode.main.scripts.ScriptItem;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypeMaterialized extends ItemType<ItemMaterialized> {
    private final String itemDescription;
    private final Color  itemColor;
    private final QualityLevel itemQuality;
    public ItemTypeMaterialized(ItemMaterializedBase base, Material material, ScriptItem s) {
        super(material.getName() + " " + base.getPartName(), base.getWeight() * material.getWeight(), base.modifyStats(material), s);
        this.itemDescription = base.getDescription().replace("%s", material.getName());
        this.itemColor = material.getColor();
        this.itemQuality = material.getQualityLevel();
    }
    @Override
    public ItemMaterialized create(Random r, int level) {
        return new ItemMaterialized(this, level);
    }
    @Override
    public ItemMaterialized scaled(ItemMaterialized original, int i) {
        return new ItemMaterialized(this, i);
    }
    @Override
    public List<String> getDisplayInfo(ItemMaterialized instance) {
        return Arrays.asList(this.itemDescription);
    }
    @Override
    public QualityLevel getQualityLevel(ItemMaterialized instance) {
        return this.itemQuality;
    }
}