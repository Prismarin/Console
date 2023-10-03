package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;
import dev.duuduu.engine.Scene;

import java.util.HashMap;

public final class SceneManager {

    private Scene currentScene, nextScene;
    private final HashMap<String, Scene> registeredScenes;

    public SceneManager() {
        registeredScenes = new HashMap<>();
    }

    public void registerScene(Scene scene) {
        if (!registeredScenes.containsValue(scene)) {
            Scene put = registeredScenes.putIfAbsent(scene.getName(), scene);
            if (put != null && DuuDuuEngine.ENGINE.DEBUG()) throw new RuntimeException("Debug Exception: Double name! Get fucked lol!");
            scene.register();
        }
    }

    public void queueScene(String name) {
        nextScene = registeredScenes.get(name);
    }

    public void queueUnregisteredScene(Scene scene) {
        nextScene = scene;
    }

    public void tick(float delta) {
        // switching scene
        if (nextScene != null) {
            currentScene.onSceneLeft();
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

}
