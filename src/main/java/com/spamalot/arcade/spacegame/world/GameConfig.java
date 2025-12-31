package com.spamalot.arcade.spacegame.world;

public final class GameConfig {
    private GameConfig() {}

    // Logical viewport (camera size in world units)
    public static final float VIEW_WIDTH  = 800f;
    public static final float VIEW_HEIGHT = 600f;

    // World is 3x the viewport in each dimension
    public static final float WORLD_WIDTH  = VIEW_WIDTH * 3f;
    public static final float WORLD_HEIGHT = VIEW_HEIGHT * 3f;

    // Step 0 content
    public static final int ASTEROID_COUNT = 20;

    // Player movement (Step 0)
    public static final float PLAYER_ACCEL = 600f;
    public static final float PLAYER_MAX_SPEED = 300f;
    public static final float PLAYER_DRAG = 0.90f; // simple damping per frame
}
// Compare this snippet from src/main/java/com/yourgame/screens/GameplayScreen.java: