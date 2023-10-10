package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;

public class Script {

    public final GameObject gameObject;

    public Script(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void start() {}

    public void tick(float delta) {}

    public void render(Renderer renderer) {}

}
