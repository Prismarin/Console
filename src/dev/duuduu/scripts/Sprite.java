package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.Texture;

public class Sprite extends Script2D {

    protected Texture texture;

    public Sprite(GameObject parent, Texture texture) {
        super(parent);
        this.texture = texture;
        transform.size.x = texture.getImage().getWidth();
        transform.size.y = texture.getImage().getHeight();
    }

    public Sprite(Texture texture) {
        this(null, texture);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawTexture(texture, transform);
    }

}
