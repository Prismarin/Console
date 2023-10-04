package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;
import dev.duuduu.engine.RawScene;

import java.util.HashMap;

public final class SceneManager {

    private RawScene currentScene, nextScene;
    private final HashMap<String, RawScene> registeredScenes;

    public SceneManager() {
        registeredScenes = new HashMap<>();
    }

    public void registerScene(RawScene scene) {
        if (!registeredScenes.containsValue(scene)) {
            RawScene put = registeredScenes.putIfAbsent(scene.getName(), scene);
            if (put != null && DuuDuuEngine.ENGINE.DEBUG()) throw new RuntimeException("Debug Exception: Double name! Get fucked lol!");
            scene.register();
        }
    }

    public void queueScene(String name) {
        nextScene = registeredScenes.get(name);
    }

    public void queueUnregisteredScene(RawScene scene) {
        nextScene = scene;
    }

    public void tick(float delta) {
        // switching scene
        if (nextScene != null) {
            if (currentScene != null) currentScene.onSceneLeft();
            currentScene = nextScene;
            if (!currentScene.isRegistered()) currentScene.register();
            currentScene.onSceneEntered();
            nextScene = null;
        }

        // ticking scene
        if (currentScene != null) currentScene.tick(delta);
    }

    public void render(Renderer renderer) {
        if (currentScene != null) currentScene.render(renderer);
    }

    public Renderer getRenderer() {
        if (currentScene != null) return currentScene.getRenderer();
        return new Renderer() {
            @Override
            public void prepare() {

            }

            @Override
            public boolean isCompatibleToWindow() {
                return false;
            }

            @Override
            public void drawRectangle(int hexColor, int x, int y, int width, int height) {

            }

            @Override
            public void fillRectangle(int hexColor, int x, int y, int width, int height) {

            }

            @Override
            public void show() {

            }
        };
    }

}
