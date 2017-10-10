package com.main.stat;

import com.main.material.Material;

/**
 * Created by gtrpl on 6/9/2016.
 */
public interface StatBase {
    double getDefaultValue();
    double scale(int i, double original);
    double combine(double first, double second);
    double modify(Material resource, double original);
    String getStatName();
}