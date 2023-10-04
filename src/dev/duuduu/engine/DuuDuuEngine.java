package dev.duuduu.engine;

import dev.duuduu.engine.backend.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum DuuDuuEngine {

    ENGINE;

    public static final String VERSION = "DuuDuuEngine pre alpha 2.0.0";

    DuuDuuEngine() {
        debugInitialized = false;
        debug = false;

        resolutionWidth = DEFAULT_RESOLUTION_WIDTH;
        resolutionHeight = DEFAULT_RESOLUTION_HEIGHT;
    }

    // - DEBUG ---------------------------------------------------------------------------------------------------------
    private boolean debugInitialized;
    private boolean debug;

    public final void initializeDebug(boolean debug) {
        if (debugInitialized) return;
        debugInitialized = true;
        this.debug = debug;
    }

    public final boolean DEBUG() {
        return debug;
    }

    // - RESOLUTION ----------------------------------------------------------------------------------------------------
    private int resolutionWidth;
    public static final int DEFAULT_RESOLUTION_WIDTH = 1280;
    private int resolutionHeight;
    public static final int DEFAULT_RESOLUTION_HEIGHT = 720;

    public final void SET_RESOLUTION(int width, int height) {
        this.resolutionWidth = width;
        this.resolutionHeight = height;
    }

    public final int RESOLUTION_WIDTH() {
        return resolutionWidth;
    }

    public final int RESOLUTION_HEIGHT() {
        return resolutionHeight;
    }

    // - TPS -----------------------------------------------------------------------------------------------------------
    @EngineStart
    public int TPS = 60;

    // - THREADS -------------------------------------------------------------------------------------------------------
    @EngineStart
    public ThreadMode THREAD_MODE;

    // - WINDOW --------------------------------------------------------------------------------------------------------

    private Window window;

    public final <T extends Window> void initWindow(Class<T> windowClazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (window != null) return;
        Constructor<T> constructor = windowClazz.getConstructor();
        window = constructor.newInstance();
        window.init("DuuDuuEngine 2");
    }

    public final int WINDOW_WIDTH() {
        return window.getWidth();
    }

    public final int WINDOW_HEIGHT() {
        return window.getHeight();
    }

    public Window getWindow() {
        return window;
    }

    // - INPUT ---------------------------------------------------------------------------------------------------------

    private InputSystem inputSystem;

    public final <T extends InputSystem> void initInputSystem(Class<T> inputSystemClazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (inputSystem != null) return;
        window.setInputSystem(inputSystemClazz);
        inputSystem = window.inputSystem;
    }

    public final boolean IS_KEY_PRESSED(int... keys) {
        return inputSystem.isKeyPressed(keys);
    }

    // - SCENE ---------------------------------------------------------------------------------------------------------

    private SceneManager sceneManager;

    public final void initSceneManager() {
        System.out.println("Init SceneManager...");
        sceneManager = new SceneManager();
        System.out.println("SceneManager ready");
    }

    public final void QUEUE_SCENE(RawScene scene) {
        sceneManager.queueUnregisteredScene(scene);
    }

    public final void REGISTER_SCENE(RawScene scene) {
        sceneManager.registerScene(scene);
    }

    public final void QUEUE_SCENE(String name) {
        sceneManager.queueScene(name);
    }

    // - GAMELOOP ------------------------------------------------------------------------------------------------------

    private GameLoop gameLoop;

    public final void initGameLoop() {
        System.out.println("Init GameLoop...");
        this.gameLoop = new GameLoop(sceneManager);
        System.out.println("GameLoop is ready");
    }

    public final void startGameLoop() {
        this.gameLoop.start();
    }

}
