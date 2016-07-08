package com.jadencode.main.renderengine.particles;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.toolbox.Time;
import com.jadencode.main.renderengine.toolbox.Transform;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Particle implements Transform {
    private final static float GRAVITY = -50;
    private final Vector3f position;
    private final Vector3f velocity;
    private final Vector3f scale;
    private final Vector3f rotation;
    private float gravityEffect;
    private float lifeLength;
    private float elapsedTime = 0;

    private ParticleTexture texture;
    private Vector2f currentOffset = new Vector2f();
    private Vector2f nextOffset = new Vector2f();
    private float blendFactor;
    private float distance;

    public Particle(ParticleTexture texture, Vector3f position, float rotation, Vector3f scale, Vector3f velocity, float lifeLength, float gravityEffect) {
        this.texture = texture;
        this.lifeLength = lifeLength;
        this.gravityEffect = gravityEffect;
        this.rotation = new Vector3f(0, 0, rotation);
        this.scale = scale;
        this.velocity = velocity;
        this.position = position;
        ParticleMaster.addParticle(this);
    }
    public float getDistance() {
        return distance;
    }
    public Vector2f getCurrentOffset() {
        return currentOffset;
    }
    public Vector2f getNextOffset() {
        return nextOffset;
    }
    public float getBlendFactor() {
        return blendFactor;
    }
    public ParticleTexture getTexture() {
        return texture;
    }
    //Return true if it should be removed
    private final Vector3f change = new Vector3f();
    public boolean update(Camera camera) {
        this.velocity.y += GRAVITY * gravityEffect * Time.getFrameTimeSeconds();
        change.set(velocity);
        change.scale(Time.getFrameTimeSeconds());
        Vector3f.add(change, position, position);
        distance = Vector3f.sub(camera.getPosition(), position, null).lengthSquared();
        this.updateTexture();
        elapsedTime += Time.getFrameTimeSeconds();
        return elapsedTime >= lifeLength;
    }
    private void updateTexture() {
        float lifeFactor = this.elapsedTime / lifeLength;
        int stageCount = texture.getNumberOfRows() * texture.getNumberOfRows();
        float atlasProgress = lifeFactor * stageCount;
        int current = (int) atlasProgress;
        int next = Math.min(stageCount, current + 1);
        this.blendFactor = atlasProgress % 1;

        this.setTextureOffset(this.currentOffset, current);
        this.setTextureOffset(this.nextOffset, next);
    }
    private void setTextureOffset(Vector2f offset, int index) {
        int column = index % texture.getNumberOfRows();
        int row = index / texture.getNumberOfRows();
        offset.x = (float)column / (float)texture.getNumberOfRows();
        offset.y = (float)row / (float)texture.getNumberOfRows();
    }
    @Override
    public Vector3f getTranslation() {
        return position;
    }
    @Override
    public Vector3f getScale() {
        return scale;
    }
    @Override
    public Vector3f getRotation() {
        return rotation;
    }
    public Vector3f getVelocity() {
        return velocity;
    }
}
