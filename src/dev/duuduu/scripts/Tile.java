package dev.duuduu.scripts;

import dev.duuduu.engine.Transform;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Texture;

import java.awt.image.BufferedImage;

public abstract class Tile {
    public static StaticTile createStaticTile() {
        return null;
    }

    public static Tile createAnimatedTile() {
        return null;
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
