package com.spamalot.arcade.spacegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spamalot.arcade.spacegame.SpaceGame;
import com.spamalot.arcade.spacegame.input.InputSystem;
import com.spamalot.arcade.spacegame.render.WorldRenderer;
import com.spamalot.arcade.spacegame.world.GameConfig;
import com.spamalot.arcade.spacegame.world.GameWorld;

public class GameplayScreen extends ScreenAdapter {

    private final SpaceGame game;

    private final OrthographicCamera camera;
    private final Viewport viewport;

    private final InputSystem inputSystem = new InputSystem();
    private final GameWorld world = new GameWorld();
    private final WorldRenderer renderer = new WorldRenderer();

    public GameplayScreen(SpaceGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.VIEW_WIDTH, GameConfig.VIEW_HEIGHT, camera);
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        // Clamp delta so a hiccup doesn’t teleport things
        float dt = Math.min(delta, 1f / 30f);

        // Update world
        world.update(dt, inputSystem.poll());

        // Camera follows player, clamped to world bounds
        camera.position.set(world.player.position.x, world.player.position.y, 0f);
        clampCameraToWorld();
        camera.update();

        // Clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render world
        renderer.render(world, camera);
    }

    private void clampCameraToWorld() {
        float halfW = viewport.getWorldWidth() / 2f;
        float halfH = viewport.getWorldHeight() / 2f;

        camera.position.x = clamp(camera.position.x, halfW, GameConfig.WORLD_WIDTH - halfW);
        camera.position.y = clamp(camera.position.y, halfH, GameConfig.WORLD_HEIGHT - halfH);
    }

    private static float clamp(float v, float min, float max) {
        return Math.max(min, Math.min(max, v));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        renderer.close();
    }
}
