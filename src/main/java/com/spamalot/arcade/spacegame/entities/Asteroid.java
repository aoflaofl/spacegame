package com.spamalot.arcade.spacegame.entities;

import com.badlogic.gdx.math.MathUtils;
import com.spamalot.arcade.spacegame.world.GameConfig;

public class Asteroid extends Entity {

    public Asteroid(float x, float y) {
        position.set(x, y);
        radius = MathUtils.random(10f, 28f);

        // Slow random drift
        float angle = MathUtils.random(0f, 360f);
        float speed = MathUtils.random(10f, 40f);
        velocity.set(MathUtils.cosDeg(angle), MathUtils.sinDeg(angle)).scl(speed);
    }

    @Override
    public void update(float dt) {
        position.mulAdd(velocity, dt);

        // Step 0: bounce off world bounds
        if (position.x < radius) {
            position.x = radius;
            velocity.x *= -1f;
        } else if (position.x > GameConfig.WORLD_WIDTH - radius) {
            position.x = GameConfig.WORLD_WIDTH - radius;
            velocity.x *= -1f;
        }

        if (position.y < radius) {
            position.y = radius;
            velocity.y *= -1f;
        } else if (position.y > GameConfig.WORLD_HEIGHT - radius) {
            position.y = GameConfig.WORLD_HEIGHT - radius;
            velocity.y *= -1f;
        }
    }
}
