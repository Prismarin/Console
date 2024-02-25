package dev.duuduu.scripts;

import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Animation;
import org.jetbrains.annotations.NotNull;

public class AnimatedSprite extends Script2D {
    public Animation animation;

    public AnimatedSprite(@NotNull Animation animation) {
        this.animation = animation;
        transform.size.x = animation.getAnimationFrame().getWidth();
        transform.size.y = animation.getAnimationFrame().getHeight();
    }

    @Override
    public void tick(double delta) {
        super.tick(delta);
        this.animation.tick(delta);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawTexture(animation.getAnimationFrame(), transform);
    }
}
