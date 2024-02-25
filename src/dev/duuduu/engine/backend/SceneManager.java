package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;
import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.RawScene;
import dev.duuduu.engine.Scene;
import org.jetbrains.annotations.Nullable;

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

    public void tick(double delta) {
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
        if (currentScene != null && renderer != null) {
            renderer.prepare();
            currentScene.render(renderer);
            renderer.show();
        }
    }

    public @Nullable Renderer getRenderer() {
        if (currentScene != null) return currentScene.getRenderer();
        return null;
    }

    public GameObject getRoot() {
        if (currentScene instanceof Scene) {
            return ((Scene) currentScene).getRoot();
        }
        return null;
    }
}