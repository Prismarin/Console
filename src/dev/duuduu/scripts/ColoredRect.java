package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Transform;
import dev.duuduu.engine.Vector2;
import dev.duuduu.engine.backend.Renderer;

public class ColoredRect extends ColoredShape {
    public ColoredRect() {
        this(null);
    }

    public ColoredRect(GameObject gameObject) {
        super(gameObject);
    }

    public ColoredRect(GameObject gameObject, int color, Transform transform) {
        this(gameObject, color, transform.pos.x, transform.pos.y, transform.size.x, transform.size.y, transform.scale.x, transform.scale.y);
    }

    public ColoredRect(GameObject gameObject, int color, float x, float y, float width, float height, float scaleX, float scaleY) {
        this(gameObject);

        this.color = color;

        position.x = x;
        position.y = y;
        size.x = width;
        size.y = height;
        scale.x = scaleX;
        scale.y = scaleY;
    }

    public ColoredRect(int color, Transform transform) {
        this(null, color, transform);
    }

    public ColoredRect(int color, float x, float y, float width, float height, float scaleX, float scaleY) {
        this(null, color, x, y, width, height, scaleX, scaleY);
    }

    public ColoredRect(GameObject gameObject, int color, float x, float y, float width, float height) {
        this(gameObject, color, x, y, width, height, 1, 1);
    }

    public ColoredRect(int color, float x, float y, float width, float height) {
        this(null, color, x, y, width, height, 1, 1);
    }

    @Override
    public void render(Renderer renderer) {
        if (fill) renderer.fillRectangle(color, (int) position.x, (int) position.y, (int) (size.x * scale.x), (int) (size.y * scale.y));
        else renderer.drawRectangle(color, (int) position.x, (int) position.y, (int) (size.x * scale.x), (int) (size.y * scale.y));
    }

    @Override
    public boolean containsPoint(Vector2 point) {
        return point.x >= position.x && point.x <= position.x + (size.x * scale.x) && point.y >= position.y && point.y <= position.y + (size.y * scale.y);
    }
}