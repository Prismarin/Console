package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;

public final class GameLoop {

    private final SceneManager sceneManager;
    private boolean running;

    private int fps;

    public GameLoop(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        running = false;
        fps = 0;
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
            float delta = (now - lastTime) / 1E9f;
            fps = (int) (1 / delta);
            sceneManager.tick(delta);
            Renderer renderer = sceneManager.getRenderer();
            sceneManager.render(renderer);
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
        final long secInNano = 1000000000;
        long counter = 0;
        long lastTime = System.nanoTime();
        long now = 0;
        while (running) {
            now = System.nanoTime();
            Renderer renderer = sceneManager.getRenderer();
            sceneManager.render(renderer);
            fps += 1;
            counter += (now - lastTime);
            if (counter >= secInNano) {
                fps = 0;
                counter -= secInNano;
            }
            lastTime = now;
        }
    }

    public int getFps() {
        return fps;
    }

}
