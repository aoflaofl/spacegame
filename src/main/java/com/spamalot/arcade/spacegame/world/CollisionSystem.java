package com.spamalot.arcade.spacegame.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.spamalot.arcade.spacegame.entities.Asteroid;
import com.spamalot.arcade.spacegame.entities.Entity;
import com.spamalot.arcade.spacegame.entities.Player;

/**
 * Simple circle-based collision resolver for the space game.
 */
public class CollisionSystem {
    private final Vector2 delta = new Vector2();
    private final Vector2 normal = new Vector2();
    private final Vector2 relVel = new Vector2();

    public void handleCollisions(Player player, Array<Asteroid> asteroids) {
        handleAsteroidCollisions(asteroids);
        handlePlayerCollisions(player, asteroids);
    }

    private void handleAsteroidCollisions(Array<Asteroid> asteroids) {
        for (int i = 0; i < asteroids.size; i++) {
            Asteroid a = asteroids.get(i);
            for (int j = i + 1; j < asteroids.size; j++) {
                resolvePair(a, asteroids.get(j));
            }
        }
    }

    private void handlePlayerCollisions(Player player, Array<Asteroid> asteroids) {
        for (int i = 0; i < asteroids.size; i++) {
            resolvePair(player, asteroids.get(i));
        }
    }

    private void resolvePair(Entity a, Entity b) {
        // Vector from A to B
        delta.set(b.position).sub(a.position);
        float minDist = a.radius + b.radius;
        float dist2 = delta.len2();
        float minDist2 = minDist * minDist;
        if (dist2 >= minDist2) {
            return;
        }

        float dist = (float) Math.sqrt(dist2);
        if (dist == 0f) {
            normal.set(1f, 0f);
        } else {
            normal.set(delta).scl(1f / dist);
        }

        float overlap = minDist - dist;
        float separation = overlap * 0.5f;
        a.position.mulAdd(normal, -separation);
        b.position.mulAdd(normal, separation);

        // Reflect velocities along collision normal (elastic, equal mass)
        relVel.set(a.velocity).sub(b.velocity);
        float relAlongNormal = relVel.dot(normal);
        if (relAlongNormal <= 0f) {
            return; // Already separating
        }

        a.velocity.mulAdd(normal, -relAlongNormal);
        b.velocity.mulAdd(normal, relAlongNormal);
    }
}
