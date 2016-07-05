package com.jadencode.main.renderengine.entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class Camera {
    private static final float MOVE_SPEED = 1;

    private float distance = 50;
    private float angleAround = 0;

    private Vector3f position = new Vector3f(0, 20, 20);
    private float pitch = 20;
    private float yaw = 0;
    private float roll = 0;

    private Player player;

    public Camera(Player player) {
        this.player = player;
    }
    public void move() {
        this.calculateZoom();
        this.calculatePitch();
        this.calculateAngle();
        float dh = calculateHorizontalDistance();
        float dv = calculateVerticalDistance();
        this.calculateCameraPosition(dh, dv);
    }
    private void calculateCameraPosition(float dh, float dv) {
        float theta = this.angleAround + this.player.getRotation().y;
        float xOff = (float) (dh * Math.sin(Math.toRadians(theta)));
        float zOff = (float) (dh * Math.cos(Math.toRadians(theta)));
        this.position.y = player.getTranslation().y + dv;
        this.position.x = this.player.getTranslation().x - xOff;
        this.position.z = this.player.getTranslation().z - zOff;
        this.yaw = 180 - theta;
    }
    private float calculateHorizontalDistance() {
        return (float) (distance * Math.cos(Math.toRadians(this.pitch)));
    }
    private float calculateVerticalDistance() {
        return (float) (distance * Math.sin(Math.toRadians(this.pitch)));
    }
    private void calculateZoom() {
        float zoom = Mouse.getDWheel() * 0.1F;
        this.distance -= zoom;
    }
    private void calculatePitch() {
        if(Mouse.isButtonDown(0)) {
            float pitchChange = Mouse.getDY() * 0.1F;
            this.pitch -= pitchChange;
        }
    }
    private void calculateAngle() {
        if(Mouse.isButtonDown(0)) {
            float angleChange = Mouse.getDX() * 0.3F;
            this.angleAround -= angleChange;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}