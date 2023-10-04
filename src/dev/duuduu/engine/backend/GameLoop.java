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
        System.out.println("Starting Gameloop...");
        running = true;

        if (DuuDuuEngine.ENGINE.THREAD_MODE == ThreadMode.SINGLE) {
            Thread gameThread = new Thread(this::singleThreadLoop);
            gameThread.start();
            System.out.println("Started GameLoop on single thread");
        } else {
            Thread tickingThread = new Thread(this::multiThreadTickLoop);
            Thread renderThread = new Thread(this::multiThreadRenderLoop);
            tickingThread.start();
            renderThread.start();
            System.out.println("Started GameLoop on two threads");
        }
    }

    public void singleThreadLoop() {
        long lastTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            float delta = (now - lastTime) / 10E9f;
            sceneManager.tick(delta);
            Renderer renderer = sceneManager.getRenderer();
            renderer.prepare();
            sceneManager.render(renderer);
            renderer.show();
            lastTime = now;
        }
    }

    public void multiThreadTickLoop() {
        long lastTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            float delta = (now - lastTime) / 10E9f;
            sceneManager.tick(delta);
            lastTime = now;
        }
    }

    public void multiThreadRenderLoop() {
        while (running) {
            Renderer renderer = sceneManager.getRenderer();
            renderer.prepare();
            sceneManager.render(renderer);
            renderer.show();
        }
    }

}
