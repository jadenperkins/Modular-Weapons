package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.terrain.Terrain;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class MasterRenderer {
    private static final float fieldOfView = 70;
    private static final float nearPlane = 0.1F;
    private static final float farPlane = 1000F;

    private static final float RED = 0.7F;
    private static final float GREEN = 0.7F;
    private static final float BLUE = 0.7F;

    private static final float FOG_DENSITY = 0.007f;
    private static final float FOG_GRADIENT = 5.0f;

    private Matrix4f projectionMatrix = createProjectionMatrix();

    private EntityShader entityShader = new EntityShader();
    private EntityRenderer entityRenderer = new EntityRenderer(entityShader, projectionMatrix);
    private HashMap<TexturedModel, List<Entity>> entities = new HashMap<>();

    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    private List<Terrain> terrains = new ArrayList<>();

    public MasterRenderer() {
        enableCulling();
    }

    public static void enableCulling() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public static void disableCulling() {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    private static Matrix4f createProjectionMatrix() {
        Matrix4f projectionMatrix = new Matrix4f();
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1F / Math.tan(Math.toRadians(fieldOfView / 2F))) * aspectRatio);
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

    public void render(List<Light> lights, Camera camera) {
        this.prepare();
        this.entityShader.start();
        this.entityShader.SKY_COLOR.load(new Vector3f(RED, GREEN, BLUE));
        this.entityShader.FOG_DENSITY.load(FOG_DENSITY);
        this.entityShader.FOG_GRADIENT.load(FOG_GRADIENT);
        this.entityShader.LIGHT_POSITION.load(lights.stream().map(Light::getPosition).collect(Collectors.toList()));
        this.entityShader.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
        this.entityShader.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
        this.entityShader.loadLights(lights);
        this.entityShader.VIEW_MATRIX.load(Maths.createViewMatrix(camera));
        this.entityRenderer.render(this.entities);
        this.entityShader.stop();
        this.entities.clear();

        this.terrainShader.start();
        this.terrainShader.SKY_COLOR.load(new Vector3f(RED, GREEN, BLUE));
        this.terrainShader.FOG_DENSITY.load(FOG_DENSITY);
        this.terrainShader.FOG_GRADIENT.load(FOG_GRADIENT);
        this.terrainShader.LIGHT_POSITION.load(lights.stream().map(Light::getPosition).collect(Collectors.toList()));
        this.terrainShader.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
        this.terrainShader.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
        this.terrainShader.VIEW_MATRIX.load(Maths.createViewMatrix(camera));
        this.terrainRenderer.render(this.terrains);
        this.terrainShader.stop();
        this.terrains.clear();
    }

    public void processTerrain(Terrain terrain) {
        this.terrains.add(terrain);
    }

    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        if (!this.entities.containsKey(model)) this.entities.put(model, new ArrayList<>());
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
}