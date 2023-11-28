package dev.duuduu.engine;

import dev.duuduu.engine.backend.EngineEvent;
import dev.duuduu.engine.backend.Renderer;

public abstract class RawScene {

    protected String name;
    private boolean registered;

    public RawScene(String name) {
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
    public abstract Renderer getRenderer();

    @EngineEvent
    public abstract void onSceneEntered();

    @EngineEvent
    public abstract void onSceneLeft();

    @EngineEvent
    public abstract void tick(double delta);

    @EngineEvent
    public abstract void render(Renderer renderer);

    /**
     * only overwrite if you know what you are doing!
     * @return the name of the scene, used by @{@link dev.duuduu.engine.backend.SceneManager} when queuing a scene by name
     */
    public String getName() {
        return name;
    }

    public final boolean isRegistered() {
        return registered;
    }

}
