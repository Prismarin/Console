package dev.duuduu.console;

import dev.duuduu.engine.backend.InputSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleJInputSystem extends InputSystem implements KeyListener, ControllerButtons {
    public static final int CONTROLLER_SHIFT = 16;

    private boolean[] pressedKeys;

    @Override
    public void init() {
        pressedKeys = new boolean[4096];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;
    }

    @Override
    public synchronized boolean isKeyPressed(int... keys) {
        return pressedKeys[keys[0] + CONTROLLER_SHIFT * keys[1]];
    }

    // - unused --------------------------------------------------------------------------------------------------------

    @Override
    public void poll() {}

    @Override
    public void keyTyped(KeyEvent e) {}
}