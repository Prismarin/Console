package dev.duuduu.console.backend;

public abstract class InputSystem {

    public abstract void init();

    public abstract void poll();

    public abstract boolean isKeyPressed(int... key);

}
