package dev.duuduu.engine;

import dev.duuduu.engine.backend.legacyCore.JRenderer;
import dev.duuduu.engine.backend.Renderer;

public class Scene extends RawScene {

    private final Renderer defaultRenderer;
    private final GameObject root;

    public Scene(String name, GameObject root) {
        super(name);
        defaultRenderer = new JRenderer();
        this.root = root;
    }

    @Override
    public void onRegister() {

    }

    @Override
    public Renderer getRenderer() {
        return defaultRenderer;
    }

    @Override
    public void onSceneEntered() {
        root.start();
    }

    @Override
    public void onSceneLeft() {

    }

    @Override
    public void tick(float delta) {
        root.tick(delta);
    }

    @Override
    public void render(Renderer renderer) {
        root.render(renderer);
    }

    public GameObject getRoot() {
        return root;
    }

}
