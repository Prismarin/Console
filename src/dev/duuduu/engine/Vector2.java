package dev.duuduu.engine;

import org.jetbrains.annotations.NotNull;

public class Vector2 {
    public float x, y;

    public Vector2() {
        this(0, 0);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(float constant) {
        return new Vector2(x + constant, y + constant);
    }

    public Vector2 add(@NotNull Vector2 vector) {
        return new Vector2(x + vector.x, y + vector.y);
    }

    public Vector2 sub(float constant) {
        return add(-constant);
    }

    public Vector2 sub(@NotNull Vector2 vector) {
        return new Vector2(x - vector.x, y - vector.y);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        float length = length();
        return new Vector2(x / length, y / length);
    }
}