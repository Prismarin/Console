package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;

public abstract class Scene {

    protected String name;
    private boolean registered;

    public Scene(String name) {
        this.name = name;
        this.registered = false;
    }

    public final void register() {
        if (!registered) {
            registered = true;
            onRegister();
        }
    }

    @EngineEvent
    public abstract void onRegister();

    @EngineEvent
    public abstract void onSceneEntered();

    @EngineEvent
    public abstract void onSceneLeft();

    @EngineEvent
    public abstract void tick(float delta);

    @EngineEvent
    public abstract void render(Renderer renderer);

    public String getName() {
        return name;
    }

    public boolean isRegistered() {
        return registered;
    }

}
