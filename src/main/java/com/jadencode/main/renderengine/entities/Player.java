package com.jadencode.main.renderengine.entities;

import com.jadencode.main.renderengine.toolbox.DisplayManager;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.toolbox.Time;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Andy on 7/4/2016.
 */
public class Player extends Entity {

    private static final float SPRINT_BOOST = 30;
    private static final float WALK_SPEED = 20; //distance per second
    private static final float TURN_SPEED = 160; //degrees per second
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;
    private static final float TERRAIN_HEIGHT = 0;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;
    private boolean isInAir = false;

    public Player(TexturedModel model, Vector3f translation, Vector3f rotation, Vector3f scale) {
        super(model, translation, rotation, scale);
    }

    public void move() {
        checkInputs();
        float frameTimeSecs = Time.getFrameTimeSeconds();
        this.rotate(0, currentTurnSpeed * frameTimeSecs, 0);

        float distance = currentSpeed * frameTimeSecs;
        float dx = (float) (distance * Math.sin(Math.toRadians(this.getRotation().getY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(this.getRotation().getY())));
        this.translate(dx, 0, dz);

        this.upwardsSpeed += GRAVITY * frameTimeSecs;
        this.translate(0, upwardsSpeed * frameTimeSecs, 0);

        if (this.getTranslation().y < TERRAIN_HEIGHT) {
            upwardsSpeed = 0;
            this.getTranslation().y = TERRAIN_HEIGHT;
            this.isInAir = false;
        }
    }
    private void jump() {
        if(!isInAir) {
            this.upwardsSpeed = JUMP_POWER;
            this.isInAir = true;
        }
    }

    /**
     * Called every tick to update the player's position based on keyboard input
     */
    private void checkInputs() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpeed = -(WALK_SPEED + (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? SPRINT_BOOST : 0));
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpeed = WALK_SPEED + (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? SPRINT_BOOST : 0);
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
