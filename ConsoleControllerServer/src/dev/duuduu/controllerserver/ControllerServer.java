package dev.duuduu.controllerserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ControllerServer {
    public static void main(String[] args) throws Exception {
        System.out.println("This is a demo of the controller server.");
        // Create the Server
        ControllerServer controllerServer = new ControllerServer(6969, 15000, 35);
        controllerServer.start();
        while (true) {
            controllerServer.tick();
            ControllerInfo[] infos = controllerServer.getControllerStates();
            for (ControllerInfo info : infos) {
                if (info != null) {
                    if (info.pressedButtons[0]) {
                        System.out.println("W");
                    }
                }
            }
        }
    }

    public static short MAX_CONTROLLER_COUNT = 8;
    public static short MAX_BUTTON_COUNT = 32;
    public static short HIGHEST_BUTTON_COUNT = 11;

    private DatagramSocket server;
    private Thread thread;
    private volatile boolean running = false;
    private volatile ControllerInfo[] controllers;
    private final long timeout, button_timeout;

    public ControllerServer(int port, long timeout, long button_timeout) throws SocketException {
        this.server = new DatagramSocket(port);
        this.timeout = timeout;
        this.button_timeout = button_timeout;
        controllers = new ControllerInfo[MAX_CONTROLLER_COUNT];
    }

    public synchronized void start() {
        thread = new Thread(this::listen);
        running = true;
        thread.start();
    }

    private void listen() {
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        ControllerCmd lastCommand = new ControllerCmd();
        boolean newController;
        while (running) {
            try {
                server.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            lastCommand.assign((short) ((packet.getData()[0] << 8 | (packet.getData()[1] & 0xFF))), packet.getData()[2]);
            newController = true;
            for (int i = 0; i < MAX_CONTROLLER_COUNT; i++) {
                if (controllers[i] == null) continue;
                if (controllers[i].id == lastCommand.int16_unique_controller_id) {
                    newController = false;
                    controllers[i].setPing(System.currentTimeMillis());
                    if (lastCommand.b_justPing) {
                        break;
                    }
                    controllers[i].setButtonPressed(lastCommand.int5_button_index, lastCommand.b_press);
                    break;
                }
            }

            if (newController) {
                for (int i = 0; i < MAX_CONTROLLER_COUNT; i++) {
                    if (controllers[i] == null) {
                        controllers[i] = new ControllerInfo(lastCommand.int5_button_index, HIGHEST_BUTTON_COUNT, packet.getAddress(), packet.getPort());
                    }
                }
            }
        }
    }

    /**
     * has to be called externally by some kind of loop
     */
    public void tick() {
        long now = System.currentTimeMillis();
        for (int i = 0; i < MAX_CONTROLLER_COUNT; i++) {
            if (controllers[i] != null) {
                if (now - controllers[i].getPing() > timeout) {
                    controllers[i] = null;
                    continue;
                }

                controllers[i].tick(button_timeout);
            }
        }
    }

    public ControllerInfo[] getControllerStates() {
        return controllers;
    }

    /**
     *
     * Deprecated because currently unimplemented
     *
     * @param controllerID the controller to send the colorCode to
     * @param colorCode colorCode in hex
     */
    @Deprecated
    public void sendLightCode(long controllerID, int colorCode) {

    }

    /**
     *
     * Deprecated because currently unimplemented
     *
     * @param controllerID the controller to send the colorCode to
     * @param length duration of the rumble
     */
    @Deprecated
    public void sendRumble(long controllerID, int length) {

    }

    public synchronized void stop() {
        if (thread != null) thread.interrupt();
    }
}
