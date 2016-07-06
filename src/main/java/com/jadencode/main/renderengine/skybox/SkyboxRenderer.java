package com.jadencode.main.renderengine.skybox;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.toolbox.Maths;
import com.jadencode.main.renderengine.toolbox.Time;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/6/2016.
 */
public class SkyboxRenderer {
    private static final float SIZE = 500f;

    private static final float[] VERTICES = {
            -SIZE,  SIZE, -SIZE,
            -SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE,  SIZE, -SIZE,
            -SIZE,  SIZE, -SIZE,

            -SIZE, -SIZE,  SIZE,
            -SIZE, -SIZE, -SIZE,
            -SIZE,  SIZE, -SIZE,
            -SIZE,  SIZE, -SIZE,
            -SIZE,  SIZE,  SIZE,
            -SIZE, -SIZE,  SIZE,

            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE,  SIZE,
            SIZE,  SIZE,  SIZE,
            SIZE,  SIZE,  SIZE,
            SIZE,  SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,

            -SIZE, -SIZE,  SIZE,
            -SIZE,  SIZE,  SIZE,
            SIZE,  SIZE,  SIZE,
            SIZE,  SIZE,  SIZE,
            SIZE, -SIZE,  SIZE,
            -SIZE, -SIZE,  SIZE,

            -SIZE,  SIZE, -SIZE,
            SIZE,  SIZE, -SIZE,
            SIZE,  SIZE,  SIZE,
            SIZE,  SIZE,  SIZE,
            -SIZE,  SIZE,  SIZE,
            -SIZE,  SIZE, -SIZE,

            -SIZE, -SIZE, -SIZE,
            -SIZE, -SIZE,  SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            -SIZE, -SIZE,  SIZE,
            SIZE, -SIZE,  SIZE
    };

    private final static String[] TEXTURE_FILES = {"right", "left", "top", "bottom", "back", "front"};
    private final static String[] NIGHT_FILES = {"nightRight", "nightLeft", "nightTop", "nightBottom", "nightBack", "nightFront"};

    private final RawModel cube;
    private final int textureID;
    private final int nightTextureID;
    private final SkyboxShader shader;
    private float time = 0;
    private float rotation = 0;

    public SkyboxRenderer(Loader loader, Matrix4f projection) {
        this.cube = loader.loadToVAO(VERTICES, 3);
        this.textureID = loader.loadCubeMap(TEXTURE_FILES);
        this.nightTextureID = loader.loadCubeMap(NIGHT_FILES);
        this.shader = new SkyboxShader();
        this.shader.start();
        this.shader.connectTextureUnits();
        this.shader.PROJECTION_MATRIX.load(projection);
        this.shader.stop();
    }
    public void render(Camera camera, Vector3f fogColor) {
        this.shader.start();
        Matrix4f view = Maths.createViewMatrix(camera);
        view.m30 = 0;
        view.m31 = 0;
        view.m32 = 0;
        this.update();
        Matrix4f.rotate((float) Math.toRadians(this.rotation), new Vector3f(0, 1, 0), view, view);

        this.shader.VIEW_MATRIX.load(view);
        this.shader.FOG_COLOR.load(fogColor);
        GL30.glBindVertexArray(this.cube.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        this.bindTextures();
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        this.shader.stop();
    }
    private static final float ROTATION_SPEED = 1F;
    private void update() {
        this.rotation += ROTATION_SPEED * Time.getFrameTimeSeconds();
        this.rotation %= 360;
        time += Time.getFrameTimeSeconds() * 1000F;
        time %= 24000;
    }
    private void bindTextures() {
        int tex1;
        int tex2;
        float blend;
        if(time >= 0 && time < 5000) {
            tex1 = nightTextureID;
            tex2 = nightTextureID;
            blend = (time - 0) / 5000;
        } else if(time >= 5000 && time < 8000) {
            tex1 = nightTextureID;
            tex2 = textureID;
            blend = (time - 5000) / 3000;
        } else if(time >= 8000 & time < 21000) {
            tex1 = textureID;
            tex2 = textureID;
            blend = (time - 8000) / 3000;
        } else {
            tex1 = textureID;
            tex2 = textureID;
            blend = (time - 21000) / 3000;
        }
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, tex1);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, tex2);
        this.shader.BLEND_FACTOR.load(blend);
    }
}