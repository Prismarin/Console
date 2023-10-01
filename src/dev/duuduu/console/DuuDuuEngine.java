package dev.duuduu.console;

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

}
