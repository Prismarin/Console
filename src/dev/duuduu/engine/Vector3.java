package dev.duuduu.engine;

import org.jetbrains.annotations.NotNull;

public class Vector3 {
    public float x, y, z;

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(float constant) {
        return new Vector3(x + constant, y + constant, z + constant);
    }

    public Vector3 add(@NotNull Vector3 vector) {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3 sub(float constant) {
        return add(-constant);
    }

    public Vector3 sub(@NotNull Vector3 vector) {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        float length = length();
        return new Vector3(x / length, y / length, z / length);
    }
}
