package com.spamalot.arcade.spacegame.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.spamalot.arcade.spacegame.SpaceGame;
import com.spamalot.arcade.spacegame.world.GameConfig;

public class DesktopLauncher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Space Game");
        config.setWindowedMode(
                (int) GameConfig.VIEW_WIDTH,
                (int) GameConfig.VIEW_HEIGHT
        );
        config.useVsync(true);

        new Lwjgl3Application(new SpaceGame(), config);
    }
}
