package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;

public abstract class Scene {

    protected String name;

    public Scene(String name) {
        this.name = name;
    }

    public abstract void tick(float delta);

    public abstract void render(Renderer renderer);

}
