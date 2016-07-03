package com.jadencode.main.renderengine.toolbox;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public interface Transform {
    Vector3f getTranslation();
    Vector3f getRotation();
    Vector3f getScale();
}