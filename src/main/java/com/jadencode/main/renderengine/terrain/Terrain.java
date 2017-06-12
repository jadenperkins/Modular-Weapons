package com.jadencode.main.renderengine.terrain;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.textures.TerrainTexture;
import com.jadencode.main.renderengine.textures.TerrainTexturePack;
import com.jadencode.main.renderengine.toolbox.Maths;
import com.jadencode.main.renderengine.toolbox.Transform;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class Terrain implements Transform {
    private static final float SIZE = 800;
    private static final float MAX_HEIGHT = 40;
    private static final float MAX_PIXEL_COLOR = 256 * 256 * 256;
    private static final int VERTEX_COUNT = 128;

    private final Vector3f translation;
    private final Vector3f rotation = new Vector3f(0, 0, 0);
    private final Vector3f scale = new Vector3f(1, 1, 1);
    private float x;
    private float z;
    private RawModel model;
    private TerrainTexturePack texture;
    private TerrainTexture blendMap;
    private final HeightsGenerator heightsGenerator;

    private float[][] heights;

    public Terrain(int gridX, int gridZ, Loader loader, TerrainTexturePack texture, TerrainTexture blendMap, long seed) {
        this.texture = texture;
        this.blendMap = blendMap;
        this.x = gridX * SIZE;
        this.z = gridZ * SIZE;
        this.translation = new Vector3f(this.x, 0, this.z);
        this.heightsGenerator = new HeightsGenerator(gridX, gridZ, VERTEX_COUNT, seed);
        this.model = this.generateTerrain(loader);
    }
    public float getHeight(float x, float z) {
        float terrainX = x - this.x;
        float terrainZ = z - this.z;
        float gridSquareSize = SIZE / ((float) heights.length - 1);
        int gridX = (int) Math.floor(terrainX / gridSquareSize);
        int gridZ = (int) Math.floor(terrainZ / gridSquareSize);
        if(gridX >= heights.length - 1 || gridZ >= heights.length - 1 || gridX < 0 || gridZ < 0) {
            return 0;
        }
        float xCoord = (terrainX % gridSquareSize) / gridSquareSize;
        float zCoord = (terrainZ % gridSquareSize) / gridSquareSize;
        if(xCoord <= 1 - zCoord) {
            Vector3f p1 = new Vector3f(0, heights[gridX][gridZ], 0);
            Vector3f p2 = new Vector3f(1, heights[gridX + 1][gridZ], 0);
            Vector3f p3 = new Vector3f(0, heights[gridX][gridZ + 1], 1);
            Vector2f pos = new Vector2f(xCoord, zCoord);
            return Maths.barrycentric(p1, p2, p3, pos);
        } else {
            Vector3f p1 = new Vector3f(1, heights[gridX + 1][gridZ], 0);
            Vector3f p2 = new Vector3f(0, heights[gridX][gridZ + 1], 1);
            Vector3f p3 = new Vector3f(1, heights[gridX + 1][gridZ + 1], 1);
            Vector2f pos = new Vector2f(xCoord, zCoord);
            return Maths.barrycentric(p1, p2, p3, pos);
        }
    }

    public RawModel getModel() {
        return model;
    }

    @Override
    public Vector3f getTranslation() {
        return translation;
    }

    @Override
    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public Vector3f getScale() {
        return scale;
    }

    private RawModel generateTerrain(Loader loader) {
//        this.heights = this.heightsGenerator.generateHeights();
        heights = new float[VERTEX_COUNT][VERTEX_COUNT];

        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (VERTEX_COUNT - 1) * (VERTEX_COUNT - 1)];
        int vertexPointer = 0;
        for (int i = 0; i < VERTEX_COUNT; i++) {
            for (int j = 0; j < VERTEX_COUNT; j++) {
                vertices[vertexPointer * 3] = (float) j / ((float) VERTEX_COUNT - 1) * SIZE;
                float height = getHeight(j, i);
                heights[j][i] = height;
                vertices[vertexPointer * 3 + 1] = height;
                vertices[vertexPointer * 3 + 2] = (float) i / ((float) VERTEX_COUNT - 1) * SIZE;
                Vector3f normal = this.calculateNormal(j, i);
                normals[vertexPointer * 3] = normal.x;
                normals[vertexPointer * 3 + 1] = normal.y;
                normals[vertexPointer * 3 + 2] = normal.z;
                textureCoords[vertexPointer * 2] = (float) j / ((float) VERTEX_COUNT - 1);
                textureCoords[vertexPointer * 2 + 1] = (float) i / ((float) VERTEX_COUNT - 1);
                vertexPointer++;
            }
        }
        int pointer = 0;
        for (int gz = 0; gz < VERTEX_COUNT - 1; gz++) {
            for (int gx = 0; gx < VERTEX_COUNT - 1; gx++) {
                int topLeft = (gz * VERTEX_COUNT) + gx;
                int topRight = topLeft + 1;
                int bottomLeft = ((gz + 1) * VERTEX_COUNT) + gx;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
        return loader.loadToVAO(vertices, textureCoords, normals, indices);
    }

    public void bindTextures() {
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.backgroundTexture.textureID);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.rTexture.textureID);
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.gTexture.textureID);
        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.bTexture.textureID);
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, blendMap.textureID);
    }
    private Vector3f calculateNormal(int x, int y) {
        float heightL = getHeight(x - 1, y);
        float heightR = getHeight(x + 1, y);
        float heightU = getHeight(x, y + 1);
        float heightD = getHeight(x, y - 1);
        Vector3f normal = new Vector3f(heightL - heightR, 2, heightD - heightU);
        normal.normalise();
        return normal;
    }
    private float getHeight(int x, int z) {
        if(x < 0 || x >= VERTEX_COUNT || z < 0 || z >= VERTEX_COUNT) return 0F;
        return this.heights[x][z];
//        return this.heightsGenerator.generateHeight(x, z);
    }
}
