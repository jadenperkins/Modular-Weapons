package com.jadencode.main.renderengine.entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class Camera {
    private static final float MOVE_SPEED = 1;
    private Vector3f position = new Vector3f(0, 20, 20);
    private float pitch = 20;
    private float yaw = 0;
    private float roll = 0;

    public Camera() {

    }

    public void move() {
//        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//            this.position.z -= MOVE_SPEED;
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
//            this.position.z += MOVE_SPEED;
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
//            this.position.x -= MOVE_SPEED;
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
//            this.position.x += MOVE_SPEED;
//        }
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