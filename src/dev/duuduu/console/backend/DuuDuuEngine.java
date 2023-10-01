package dev.duuduu.console.backend;

public enum DuuDuuEngine {

    ENGINE;

    DuuDuuEngine() {
        debug = false;
    }

    // - DEBUG ---------------------------------------------------------------------------------------------------------
    private boolean debug;

    void initializeDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean DEBUG() {
        return debug;
    }

}
