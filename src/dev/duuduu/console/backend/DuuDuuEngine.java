package dev.duuduu.console.backend;

public enum DuuDuuEngine {

    ENGINE;

    DuuDuuEngine() {
        debug = false;

        resolutionWidth = DEFAULT_RESOLUTION_WIDTH;
        resolutionHeight = DEFAULT_RESOLUTION_HEIGHT;
    }

    // - DEBUG ---------------------------------------------------------------------------------------------------------
    private boolean debug;

    final void initializeDebug(boolean debug) {
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

    public final void setResolution(int width, int height) {
        this.resolutionWidth = width;
        this.resolutionHeight = height;
    }

    public final int getResolutionWidth() {
        return resolutionWidth;
    }

    public final int getResolutionHeight() {
        return resolutionHeight;
    }

}
