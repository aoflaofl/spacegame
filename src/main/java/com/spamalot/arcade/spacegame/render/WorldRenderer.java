package com.spamalot.arcade.spacegame.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.spamalot.arcade.spacegame.entities.Asteroid;
import com.spamalot.arcade.spacegame.world.GameConfig;
import com.spamalot.arcade.spacegame.world.GameWorld;

public class WorldRenderer implements AutoCloseable {
    private final ShapeRenderer shapes = new ShapeRenderer();

    public void render(GameWorld world, OrthographicCamera camera) {
        shapes.setProjectionMatrix(camera.combined);

        // Draw world bounds + entities
        shapes.begin(ShapeRenderer.ShapeType.Line);

        // World border
        shapes.rect(0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        // Player as a triangle-ish circle + facing line
        float px = world.player.position.x;
        float py = world.player.position.y;
        shapes.circle(px, py, world.player.radius);

        float fx = px + MathUtils.cosDeg(world.player.rotationDeg) * (world.player.radius + 10f);
        float fy = py + MathUtils.sinDeg(world.player.rotationDeg) * (world.player.radius + 10f);
        shapes.line(px, py, fx, fy);

        // Asteroids
        for (Asteroid a : world.asteroids) {
            shapes.circle(a.position.x, a.position.y, a.radius);
        }

        shapes.end();
    }

    @Override
    public void close() {
        shapes.dispose();
    }
}
