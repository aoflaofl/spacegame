package com.spamalot.arcade.spacegame.world;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.spamalot.arcade.spacegame.entities.Asteroid;
import com.spamalot.arcade.spacegame.entities.Player;
import com.spamalot.arcade.spacegame.input.InputState;

public class GameWorld {
    public final Player player;
    public final Array<Asteroid> asteroids = new Array<>();

    public GameWorld() {
        player = new Player(GameConfig.WORLD_WIDTH / 2f, GameConfig.WORLD_HEIGHT / 2f);

        for (int i = 0; i < GameConfig.ASTEROID_COUNT; i++) {
            float x = MathUtils.random(40f, GameConfig.WORLD_WIDTH - 40f);
            float y = MathUtils.random(40f, GameConfig.WORLD_HEIGHT - 40f);
            asteroids.add(new Asteroid(x, y));
        }
    }

    public void update(float dt, InputState input) {
        player.handleInput(input, dt);
        player.update(dt);

        // Keep player in bounds for Step 0 (simple clamp)
        player.position.x = MathUtils.clamp(player.position.x, player.radius, GameConfig.WORLD_WIDTH - player.radius);
        player.position.y = MathUtils.clamp(player.position.y, player.radius, GameConfig.WORLD_HEIGHT - player.radius);

        for (Asteroid a : asteroids) {
            a.update(dt);
        }
    }
}
