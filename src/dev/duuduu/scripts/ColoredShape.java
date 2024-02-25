package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Vector2;
import dev.duuduu.engine.backend.Renderer;

import java.awt.*;

public abstract class ColoredShape extends Script2D {
    public int color;
    public boolean fill;

    public ColoredShape(GameObject gameObject) {
        super(gameObject);

        this.color = 0xFFFFFFFF;
        this.fill = false;
    }

    public ColoredShape() {
        this(null);
    }

    @Override
    public abstract void render(Renderer renderer);

    public abstract boolean containsPoint(Vector2 point);
}