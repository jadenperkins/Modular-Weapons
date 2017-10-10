package com.main.generate;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/22/2016.
 */
public enum QualityLevel {

    COMMON(1, "Common", new Color(0xBABABA)),
    UNCOMMON(2, "Uncommon", new Color(0x1EFF00)),
    RARE(3, "Rare", new Color(0x00070DD)),
    EPIC(4, "Epic", new Color(0xA335EE)),
    LEGENDARY(5, "Legendary", new Color(0xFF8000));

    private final int qualityPoints;
    private final String qualityName;
    private final Color color;

    QualityLevel(int qualityPoints, String qualityName, Color color) {
        this.qualityPoints = qualityPoints;
        this.qualityName = qualityName;
        this.color = color;
    }
    public int getQualityPoints() {
        return qualityPoints;
    }
    public String getQualityName() {
        return qualityName;
    }
    public Color getColor() {
        return color;
    }
    public static QualityLevel calculate(QualityObject object) {
        List<QualityLevel> list = object.getQualityLevels();
        List<QualityValue> ordered = new ArrayList<>();
        for (QualityLevel level : values()) {
            double sum = 0;
            for (QualityLevel obj : list) {
                double diff = obj.qualityPoints - level.qualityPoints;
                sum += Math.pow(diff, 2);
            }
            double sq = Math.sqrt(sum);
            ordered.add(new QualityValue(sq, level));
        }
        ordered.sort(null);
        return ordered.get(0).level;
    }
    private static class QualityValue implements Comparable<QualityValue> {
        private final double closeness;
        private final QualityLevel level;

        public QualityValue(double closeness, QualityLevel level) {
            this.closeness = closeness;
            this.level = level;
        }
        @Override
        public int compareTo(QualityValue o) {
            int d = Double.compare(this.closeness, o.closeness);
            return d == 0 ? Integer.compare(this.level.ordinal(), o.level.ordinal()) : d;
        }
    }
}