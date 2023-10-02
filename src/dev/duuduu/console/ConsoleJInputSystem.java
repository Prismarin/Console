package dev.duuduu.console;

import dev.duuduu.engine.backend.InputSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleJInputSystem extends InputSystem implements KeyListener {

    private boolean[] pressedKeys;

    @Override
    public void init() {
        pressedKeys = new boolean[4096];
    }

    @Override
    public boolean isKeyPressed(int... key) {
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // - unused --------------------------------------------------------------------------------------------------------

    @Override
    public void poll() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
