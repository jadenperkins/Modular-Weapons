package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.skybox.SkyboxRenderer;
import com.jadencode.main.renderengine.terrain.Terrain;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class MasterRenderer {
    private static final float fieldOfView = 70;
    public static final float nearPlane = 0.1F;
    public static final float farPlane = 1000F;

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

    private SkyboxRenderer skyboxRenderer;

    private NormalMappingShader normalMappingShader = new NormalMappingShader();
    private NormalMappingRenderer normalMappingRenderer = new NormalMappingRenderer(normalMappingShader, projectionMatrix);
    private Map<TexturedModel, List<Entity>> normalMapEntities = new HashMap<>();

    public MasterRenderer(Loader loader) {
        enableCulling();
        this.skyboxRenderer = new SkyboxRenderer(loader, this.projectionMatrix);
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
    public void renderScene(List<Entity> entities, List<Entity> normal, List<Terrain> terrains, List<Light> lights, Camera camera, Vector4f clipPlane) {
        terrains.forEach(this::processTerrain);
        entities.forEach(this::processEntity);
        for (Entity entity : normal) {
            this.processNormalMapEntity(entity);
        }
//        normal.forEach(this::processNormalMapEntity);
        render(lights, camera, clipPlane);
    }
    public static Vector3f getSkyColor() {
        return new Vector3f(RED, GREEN, BLUE);
    }
    public void render(List<Light> lights, Camera camera, Vector4f clipPlane) {
        Vector3f fogColor = new Vector3f(RED, GREEN, BLUE);
        this.prepare();
        this.entityShader.start();
        this.entityShader.CLIP_PLANE.load(clipPlane);
        this.entityShader.SKY_COLOR.load(fogColor);
        this.entityShader.FOG_DENSITY.load(FOG_DENSITY);
        this.entityShader.FOG_GRADIENT.load(FOG_GRADIENT);
        this.entityShader.LIGHT_POSITION.load(lights.stream().map(Light::getPosition).collect(Collectors.toList()));
        this.entityShader.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
        this.entityShader.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
        this.entityShader.VIEW_MATRIX.load(Maths.createViewMatrix(camera));
        this.entityRenderer.render(this.entities);
        this.entityShader.stop();
        this.entities.clear();

        this.normalMappingShader.start();
        this.normalMappingShader.PLANE.load(clipPlane);
        this.normalMappingShader.SKY_COLOR.load(fogColor);
        this.normalMappingShader.FOG_DENSITY.load(FOG_DENSITY);
        this.normalMappingShader.FOG_GRADIENT.load(FOG_GRADIENT);
        this.normalMappingShader.LIGHT_POSITION_EYES.load(lights.stream().map(l -> NormalMappingShader.getEyeSpacePosition(l, Maths.createViewMatrix(camera))).collect(Collectors.toList()));
        this.normalMappingShader.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
        this.normalMappingShader.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
        this.normalMappingShader.VIEW_MATRIX.load(Maths.createViewMatrix(camera));
        this.normalMappingRenderer.render(normalMapEntities);
        this.normalMappingShader.stop();
        this.normalMapEntities.clear();

        this.terrainShader.start();
        this.terrainShader.CLIP_PLANE.load(clipPlane);
        this.terrainShader.SKY_COLOR.load(fogColor);
        this.terrainShader.FOG_DENSITY.load(FOG_DENSITY);
        this.terrainShader.FOG_GRADIENT.load(FOG_GRADIENT);
        this.terrainShader.LIGHT_POSITION.load(lights.stream().map(Light::getPosition).collect(Collectors.toList()));
        this.terrainShader.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
        this.terrainShader.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
        this.terrainShader.VIEW_MATRIX.load(Maths.createViewMatrix(camera));
        this.terrainRenderer.render(this.terrains);
        this.terrainShader.stop();
        this.terrains.clear();

        this.skyboxRenderer.render(camera, fogColor);
    }
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public void processTerrain(Terrain terrain) {
        this.terrains.add(terrain);
    }

    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        if (!this.entities.containsKey(model)) this.entities.put(model, new ArrayList<>());
        this.entities.get(model).add(entity);
    }
    public void processNormalMapEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        if (!this.normalMapEntities.containsKey(model)) this.normalMapEntities.put(model, new ArrayList<>());
        this.normalMapEntities.get(model).add(entity);
    }

    public void cleanUp() {
        this.entityShader.cleanUp();
        this.terrainShader.cleanUp();
        this.normalMappingRenderer.cleanUp();
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(RED, GREEN, BLUE, 1);
    }
}