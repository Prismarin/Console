package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;

public final class GameLoop {

    private final SceneManager sceneManager;
    private boolean running;

    public GameLoop(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        running = false;
    }

    public void start() {
        if (running) return;
        running = true;

        if (DuuDuuEngine.ENGINE.THREAD_MODE == ThreadMode.SINGLE) {
            Thread gameThread = new Thread(this::singleThreadLoop);
            gameThread.start();
        } else {
            Thread tickingThread = new Thread(this::multiThreadTickLoop);
            Thread renderThread = new Thread(this::multiThreadRenderLoop);
            tickingThread.start();
            renderThread.start();
        }
    }

    public void singleThreadLoop() {
        long lastTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            float delta = (now - lastTime) / 10E9f;
            sceneManager.tick(delta);
            sceneManager.render(sceneManager.getRenderer());
            lastTime = now;
        }
    }

    public void multiThreadTickLoop() {

    }

    public void multiThreadRenderLoop() {

    }

}
