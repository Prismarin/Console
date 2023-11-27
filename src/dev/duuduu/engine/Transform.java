package dev.duuduu.engine;

public class Transform {

    public final Vector2 position, pos;

    public final Vector2 size;

    public final Vector2 scale;

    public Transform() {
        this(new Vector2(), new Vector2());
    }

    public Transform(Vector2 position, Vector2 size) {
        this.position = position;
        this.pos = this.position;
        this.size = size;
        this.scale = new Vector2(1, 1);
    }

    public Transform(Vector2 position, Vector2 size, Vector2 scale) {
        this(position, size);
        this.scale.x = scale.x;
        this.scale.y = scale.y;
    }

}
