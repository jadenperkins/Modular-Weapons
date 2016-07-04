package com.jadencode.main.renderengine.models;

/**
 * Created by gtrpl on 7/2/2016.
 */
public class RawModel {
    private final int vaoID;
    private final int vertexCount;

    public RawModel(int i, int j) {
        this.vaoID = i;
        this.vertexCount = j;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

}
