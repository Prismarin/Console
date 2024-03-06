package dev.duuduu.scripts;

import dev.duuduu.engine.Transform;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Animation;
import dev.duuduu.resources.Texture;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class Tile {
    @Contract("_ -> new")
    public static @NotNull StaticTile createStaticTile(Texture texture) {
        return new StaticTile(texture);
    }

    @Contract("_ -> new")
    public static @NotNull AnimatedTile createAnimatedTile(Animation animation) {
        return new AnimatedTile(animation);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public abstract void tick(double delta);

    public abstract void render(Renderer renderer, Transform transform);

    public abstract int numberOfFrames();

    public abstract double animWaitTime();

    public abstract Texture bake(int frame);

    public abstract boolean isDuplicateAllowed();

    public abstract boolean needsTicking();
}
