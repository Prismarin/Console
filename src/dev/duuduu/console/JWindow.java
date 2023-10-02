package dev.duuduu.console;

import dev.duuduu.console.backend.Window;

import javax.swing.*;
import java.awt.*;

public class JWindow extends Window {

    private JFrame frame;
    private Canvas canvas;

    @Override
    public void init(String... args) {
        frame = new JFrame();
        frame.setTitle(args[0]);
        frame.setResizable(false);
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
    public void startLoop() {

    }

    @Override
    public void loop() {

    }

}
