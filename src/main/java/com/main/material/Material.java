package com.main.material;

import com.main.generate.QualityLevel;
import com.main.util.WeightedItem;

import java.awt.*;

public interface Material extends WeightedItem {
    Color getColor();
    String getName();
    float getMultiplier();
    int getLevel();
    MaterialType getMaterialType();
    QualityLevel getQualityLevel();
}
