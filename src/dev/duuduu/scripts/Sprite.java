package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Texture;
import org.jetbrains.annotations.NotNull;

public class Sprite extends Script2D {
    protected Texture texture;

    public Sprite(GameObject parent, @NotNull Texture texture) {
        super(parent);
        this.texture = texture;
        transform.size.x = texture.getWidth();
        transform.size.y = texture.getHeight();
        syncParentPos = true;
    }

    public Sprite(Texture texture) {
        this(null, texture);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawTexture(texture, transform);
    }
}