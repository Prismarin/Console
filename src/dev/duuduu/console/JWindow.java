package dev.duuduu.console;

import dev.duuduu.console.backend.InputSystem;
import dev.duuduu.console.backend.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JWindow extends Window {

    private JFrame frame;
    private Canvas canvas;

    @Override
    public void init(String... args) {
        frame = new JFrame();
        frame.setTitle(args[0]);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setFocusable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        if (!DuuDuuEngine.ENGINE.DEBUG()) {
            frame.setUndecorated(true);
            frame.setAlwaysOnTop(true);
            canvas.setPreferredSize(tk.getScreenSize());
        } else {
            canvas.setPreferredSize(new Dimension(DuuDuuEngine.ENGINE.RESOLUTION_WIDTH(), DuuDuuEngine.ENGINE.RESOLUTION_HEIGHT()));
        }
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public <T extends InputSystem> void setInputSystem(Class<T> inputSystemClazz)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (inputSystem == null) return;
        Constructor<T> constructor = inputSystemClazz.getConstructor();
        inputSystem = constructor.newInstance();
        if (!(inputSystem instanceof KeyListener)) throw new RuntimeException("Wrong Input System for this Window");
        inputSystem.init();
        frame.addKeyListener((KeyListener) inputSystem);
    }

    @Override
    public void startLoop() {

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

}
