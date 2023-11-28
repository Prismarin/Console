package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;

public class Script {

    protected GameObject gameObject;

    public Script(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Script() {
        this.gameObject = null;
    }

    public void setGameObject(GameObject gameObject) {
        if (this.gameObject == null) this.gameObject = gameObject;
    }

    public void start() {}

    public void tick(double delta) {}

    public void render(Renderer renderer) {}

}
