package dev.duuduu.engine.backend;

public abstract class InputSystem {
    public abstract void init();

    public abstract void poll();

    public abstract boolean isKeyPressed(int... key);
}