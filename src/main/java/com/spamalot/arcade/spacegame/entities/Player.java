package com.spamalot.arcade.spacegame.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.spamalot.arcade.spacegame.input.InputState;
import com.spamalot.arcade.spacegame.world.GameConfig;

public class Player extends Entity {

    // Facing angle in degrees (0 = right, 90 = up)
    public float rotationDeg = 90f;

    public Player(float x, float y) {
        position.set(x, y);
        radius = 14f;
    }

    public void handleInput(InputState input, float dt) {
        // Rotate
        if (input.left)  rotationDeg += 180f * dt;
        if (input.right) rotationDeg -= 180f * dt;

        // Thrust forward/back along facing direction
        Vector2 dir = new Vector2(MathUtils.cosDeg(rotationDeg), MathUtils.sinDeg(rotationDeg));

        if (input.thrust) {
            velocity.mulAdd(dir, GameConfig.PLAYER_ACCEL * dt);
        }
        if (input.brake) {
            velocity.mulAdd(dir, -GameConfig.PLAYER_ACCEL * 0.6f * dt);
        }

        // Clamp speed
        float speed = velocity.len();
        if (speed > GameConfig.PLAYER_MAX_SPEED) {
            velocity.scl(GameConfig.PLAYER_MAX_SPEED / speed);
        }

        // Simple damping (not physically correct, but fine for Step 0)
        velocity.scl(GameConfig.PLAYER_DRAG);
    }

    @Override
    public void update(float dt) {
        position.mulAdd(velocity, dt);
    }
}
