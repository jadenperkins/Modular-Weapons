package com.jadencode.main.renderengine.entities;

import com.jadencode.main.renderengine.DisplayManager;
import com.jadencode.main.renderengine.models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Andy on 7/4/2016.
 */
public class Player extends Entity {

    private static final float RUN_SPEED = 20; //distance per second
    private static final float TURN_SPEED = 160; //degrees per second
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;
    private static final float TERRAIN_HEIGHT = 0;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;

    public Player(TexturedModel model, Vector3f translation, Vector3f rotation, Vector3f scale) {
        super(model, translation, rotation, scale);
    }

    public void move() {
        checkInputs();
        float frameTimeSecs = DisplayManager.getRenderMs() / 1000f;
        super.rotate(0, currentTurnSpeed * frameTimeSecs, 0);

        float distance = currentSpeed * frameTimeSecs;
        float dx = (float) (distance * Math.sin(Math.toRadians(getYRot())));
        float dz = (float) (distance * Math.cos(Math.toRadians(getYRot())));
        super.translate(dx, 0, dz);
        upwardsSpeed += GRAVITY * frameTimeSecs;
        super.setPosition(0, upwardsSpeed * frameTimeSecs , 0);
        if (super.getTranslation().y < TERRAIN_HEIGHT) {
            upwardsSpeed = 0;
            super.getTranslation().y = TERRAIN_HEIGHT;
        }
    }

    private void jump() {
        this.upwardsSpeed = JUMP_POWER;
    }

    /**
     * Called every tick to update the player's position based on keyboard input
     */
    private void checkInputs() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            currentTurnSpeed = TURN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            currentTurnSpeed = -TURN_SPEED;
        } else {
            this.currentTurnSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump();
        }
    }

}
