## Build & Run
- Build: mvn clean compile
- Run: mvn exec:java

## Code Style
- Keep allocations out of per-frame loops (avoid new Vector2 in update).
- Prefer simple systems over ECS until collisions/enemies/boss exist.
- World units are in logical units (VIEW_WIDTH/HEIGHT), not pixels.

## Current Goal
Step 1: Add collision scaffolding
- Player vs asteroid rebound
- Asteroid vs asteroid rebound
- No jitter, no sticking
- Keep it simple: circle-based collisions
- World units are consistent across simulation/rendering.

## Constraints
- Desktop first (LWJGL3).
- World is 3x viewport in each dimension.
