package dev.duuduu.scripts;

import dev.duuduu.engine.Transform;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Texture;

public class StaticTile extends Tile {
    protected Texture texture;

    public StaticTile(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void tick(double delta) {}

    @Override
    public void render(Renderer renderer, Transform transform) {
        renderer.drawTexture(texture, transform);
    }

    @Override
    public int numberOfFrames() {
        return 1;
    }

    @Override
    public double animWaitTime() {
        return 0;
    }

    @Override
    public Texture bake(int frame) {
        return texture;
    }

    @Override
    public boolean isDuplicateAllowed() {
        return false;
    }

    @Override
    public boolean needsTicking() {
        return false;
    }
}
