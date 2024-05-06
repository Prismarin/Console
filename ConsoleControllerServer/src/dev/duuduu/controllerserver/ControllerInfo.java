package dev.duuduu.controllerserver;

import java.net.InetAddress;

public class ControllerInfo {
    public final short id;
    private long ping;
    public final boolean[] pressedButtons;
    private final long[] buttonPing;
    final InetAddress address;
    final int port;

    ControllerInfo(short id, short buttonCount, InetAddress address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
        this.ping = System.currentTimeMillis();
        this.pressedButtons = new boolean[buttonCount];
        this.buttonPing = new long[buttonCount];
    }

    void tick(long buttonTimeOut) {
        for (int i = 0; i < buttonPing.length; i++) {
            if (buttonPing[i] > buttonTimeOut) {
                pressedButtons[i] = false;
            }
        }
    }

    void setButtonPressed(short button, boolean pressed) {
        pressedButtons[button] = pressed;
        buttonPing[button] = System.currentTimeMillis();
    }

    void setPing(long ping) {
        this.ping = ping;
    }

    public long getPing() {
        return ping;
    }
}
