package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.terrain.Terrain;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class MasterRenderer {
    private static final float fieldOfView = 70;
    private static final float nearPlane = 0.1F;
    private static final float farPlane = 1000F;

    private static final float RED = 0.5F;
    private static final float GREEN = 0.5F;
    private static final float BLUE = 0.5F;

    private Matrix4f projectionMatrix = createProjectionMatrix();

    private EntityShader entityShader = new EntityShader();
    private EntityRenderer entityRenderer = new EntityRenderer(entityShader, projectionMatrix);
    private HashMap<TexturedModel, List<Entity>> entities = new HashMap<>();

    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    private List<Terrain> terrains = new ArrayList<>();

    public MasterRenderer() {
        this.enableCulling();
    }
    public static void enableCulling() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }
    public static void disableCulling() {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
    public void render(Light sun, Camera camera) {
        this.prepare();
        this.entityShader.start();
        this.entityShader.loadSkyColor(new Vector3f(RED, GREEN, BLUE));
        this.entityShader.loadLight(sun);
        this.entityShader.loadViewMatrix(camera);
        this.entityRenderer.render(this.entities);
        this.entityShader.stop();
        this.entities.clear();

        this.terrainShader.start();
        this.terrainShader.loadSkyColor(new Vector3f(RED, GREEN, BLUE));
        this.terrainShader.loadLight(sun);
        this.terrainShader.loadViewMatrix(camera);
        this.terrainRenderer.render(this.terrains);
        this.terrainShader.stop();
        this.terrains.clear();
    }
    public void processTerrain(Terrain terrain) {
        this.terrains.add(terrain);
    }
    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        if(!this.entities.containsKey(model)) this.entities.put(model, new ArrayList<>());
        this.entities.get(model).add(entity);
    }
    public void cleanUp() {
        this.entityShader.cleanUp();
        this.terrainShader.cleanUp();
    }
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(RED, GREEN, BLUE, 1);
    }
    private static Matrix4f createProjectionMatrix() {
        Matrix4f projectionMatrix = new Matrix4f();
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float)((1F / Math.tan(Math.toRadians(fieldOfView / 2F))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = farPlane - nearPlane;
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((farPlane + nearPlane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * nearPlane * farPlane) / frustum_length);
        projectionMatrix.m33 = 0;
        return projectionMatrix;
    }
}