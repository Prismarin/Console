package dev.duuduu.scripts;

import dev.duuduu.engine.Transform;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Animation;
import dev.duuduu.resources.Texture;

public class AnimatedTile extends Tile {
    protected Animation animation;

    public AnimatedTile(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void tick(double delta) {
        animation.tick(delta);
    }

    @Override
    public void render(Renderer renderer, Transform transform) {
        renderer.drawTexture(animation.getAnimationFrame(), transform);
    }

    @Override
    public int numberOfFrames() {
        return animation.frameCount();
    }

    @Override
    public double animWaitTime() {
        return animation.timer.waitTime;
    }

    @Override
    public Texture bake(int frame) {
        return animation.getFrame(frame);
    }

    @Override
    public boolean isDuplicateAllowed() {
        return true;
    }

    @Override
    public boolean needsTicking() {
        return true;
    }
}
