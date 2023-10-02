package dev.duuduu.engine;

import dev.duuduu.engine.backend.InputSystem;
import dev.duuduu.engine.backend.Window;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum DuuDuuEngine {

    ENGINE;

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
        window.init("Test");
    }

    public final int WINDOW_WIDTH() {
        return window.getWidth();
    }

    public final int WINDOW_HEIGHT() {
        return window.getHeight();
    }

    // - INPUT ---------------------------------------------------------------------------------------------------------

    private InputSystem inputSystem;

    public final <T extends InputSystem> void initInputSystem(Class<T> inputSystemClazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        window.setInputSystem(inputSystemClazz);
        inputSystem = window.inputSystem;
    }

    public final boolean IS_KEY_PRESSED(int... keys) {
        return inputSystem.isKeyPressed(keys);
    }

    // - Scene ---------------------------------------------------------------------------------------------------------



}
