package com.spamalot.arcade.spacegame.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem {
    private final InputState state = new InputState();

    public InputState poll() {
        state.left   = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
        state.right  = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        state.thrust = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
        state.brake  = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
        state.toggleDebugCollisions = Gdx.input.isKeyJustPressed(Input.Keys.F1);
        return state;
    }
}
