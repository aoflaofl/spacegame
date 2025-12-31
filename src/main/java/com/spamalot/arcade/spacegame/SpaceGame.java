package com.spamalot.arcade.spacegame;

import com.badlogic.gdx.Game;
import com.spamalot.arcade.spacegame.screens.GameplayScreen;

public class SpaceGame extends Game {
  @Override
  public void create() {
    setScreen(new GameplayScreen(this));
  }
}
