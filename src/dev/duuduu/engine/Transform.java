package dev.duuduu.engine;

public class Transform {

    public final Vector2 position, pos;

    public final Vector2 size;

    public Transform() {
        this(new Vector2(), new Vector2());
    }

    public Transform(Vector2 position, Vector2 size) {
        this.position = position;
        this.pos = this.position;
        this.size = size;
    }

}
