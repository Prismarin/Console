package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JWindow extends Window {

    private JFrame frame;
    private Canvas canvas;

    @Override
    public void init(String... args) {
        System.out.println("Init a JWindow");
        frame = new JFrame();
        frame.setTitle(args[0]);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setFocusable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        System.out.printf("Display size: %d,%d\n", tk.getScreenSize().width, tk.getScreenSize().height);
        if (!DuuDuuEngine.ENGINE.DEBUG()) {
            frame.setUndecorated(true);
            frame.setAlwaysOnTop(true);
            canvas.setPreferredSize(tk.getScreenSize());
        } else {
            System.out.println("Setting window's size to resolution size");
            canvas.setPreferredSize(new Dimension(DuuDuuEngine.ENGINE.RESOLUTION_WIDTH(), DuuDuuEngine.ENGINE.RESOLUTION_HEIGHT()));
        }
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Window visible");
    }

    @Override
    public <T extends InputSystem> void setInputSystem(Class<T> inputSystemClazz)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (inputSystem != null) return;
        System.out.println("Setting InputSystem...");
        Constructor<T> constructor = inputSystemClazz.getConstructor();
        inputSystem = constructor.newInstance();
        if (!(inputSystem instanceof KeyListener)) throw new RuntimeException("Incompatible InputSystem for JWindow");
        inputSystem.init();
        frame.addKeyListener((KeyListener) inputSystem);
        System.out.println("InputSystem active");
    }

    @Override
    public void loop() {

    }

    @Override
    public int getWidth() {
        return canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return canvas.getHeight();
    }

    @Override
    public String getTitle() {
        return frame.getTitle();
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public BufferStrategy getBufferStrategy() {
        if (canvas.getBufferStrategy() == null) canvas.createBufferStrategy(3);
        return canvas.getBufferStrategy();
    }

}
