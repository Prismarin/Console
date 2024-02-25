package dev.duuduu.engine.backend.legacyCore;

import dev.duuduu.engine.backend.InputSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JInputSystem extends InputSystem implements KeyListener {
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
    public synchronized boolean isKeyPressed(int... key) {
        return pressedKeys[key[0]];
    }

    // - unused --------------------------------------------------------------------------------------------------------

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void poll() {}
}