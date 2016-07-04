package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.models.TexturedModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class MasterRenderer {
    private StaticShader shader = new StaticShader();
    private Renderer renderer = new Renderer(shader);
    private HashMap<TexturedModel, List<Entity>> entities = new HashMap<>();

    public void render(Light sun, Camera camera) {
        this.renderer.prepare();
        this.shader.start();
        this.shader.loadLight(sun);
        this.shader.loadViewMatrix(camera);
        renderer.render(this.entities);
        this.shader.start();
        this.entities.clear();
    }
    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        if(!this.entities.containsKey(model)) this.entities.put(model, new ArrayList<>());
        this.entities.get(model).add(entity);
    }
    public void cleanUp() {
        this.shader.cleanUp();
    }
}