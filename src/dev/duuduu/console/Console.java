package dev.duuduu.console;

public enum Console {

    CONSOLE;

    public void init() {
        if (consoleActive) {
            System.err.println("Console already active!");
            return;
        }
        consoleActive = true;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private boolean consoleActive = false;

    public boolean IS_CONSOLE_ACTIVE() {
        return consoleActive;
    }

}
