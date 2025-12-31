package com.spamalot.arcade.spacegame.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    public final Vector2 position = new Vector2();
    public final Vector2 velocity = new Vector2();
    public float radius = 10f;
    public boolean alive = true;

    public abstract void update(float dt);
}
