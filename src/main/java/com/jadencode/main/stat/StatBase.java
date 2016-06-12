package com.jadencode.main.stat;

import com.jadencode.main.material.MaterialResource;

/**
 * Created by gtrpl on 6/9/2016.
 */
public interface StatBase<T> {
    T getDefaultValue();
    T scale(int i, T original);
    T combine(T first, T second);
    T modify(MaterialResource resource, T original);
    String getStatName();
}