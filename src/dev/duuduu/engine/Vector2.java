package dev.duuduu.engine;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

    public Vector2 sMul(float s) {
        return new Vector2(x * s, y * s);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        float length = length();
        return new Vector2(x / length, y / length);
    }

    @Override
    public Vector2 clone() {
        return new Vector2(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2 vector2)) return false;
        return Float.compare(vector2.x, x) == 0 && Float.compare(vector2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}