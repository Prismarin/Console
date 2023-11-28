package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;

public final class GameLoop {

    private final SceneManager sceneManager;
    private boolean running;
    @NotGarbage
    private String s;

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
        try {
            DuuDuuEngine.ENGINE.getWindow().threadInit();
            long lastTime = System.nanoTime();
            while (running) {
                long now = System.nanoTime();
                double delta = (now - lastTime) / 1_000_000_000d;
                s = String.format("%f", delta);
                fps = (int) (1 / delta);
                sceneManager.tick(delta);
                Renderer renderer = sceneManager.getRenderer();
                sceneManager.render(renderer);
                lastTime = now;
            }
        } catch (Exception e) {
            if (DuuDuuEngine.ENGINE.DEBUG()) throw new RuntimeException(e);
        }
    }

    public void multiThreadTickLoop() {
        try {
            long lastTime = System.nanoTime();
            while (running) {
                long now = System.nanoTime();
                double delta = (now - lastTime) / 1_000_000_000d;
                s = String.format("%f", delta);
                sceneManager.tick(delta);
                lastTime = now;
            }
        } catch (Exception e) {
            if (DuuDuuEngine.ENGINE.DEBUG()) throw new RuntimeException(e);
        }
    }

    public void multiThreadRenderLoop() {
        DuuDuuEngine.ENGINE.getWindow().threadInit();
        try {
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
        } catch (Exception e) {
            if (DuuDuuEngine.ENGINE.DEBUG()) throw new RuntimeException(e);
        }
    }

    public int getFps() {
        return fps;
    }

    public void stop() {
        running = false;
    }

}
