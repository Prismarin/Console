package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;
import dev.duuduu.engine.Vector2;
import dev.duuduu.resources.Texture;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class JRenderer extends Renderer {

    private final Window window;
    private final BufferStrategy bs;
    private Graphics g;
    private BufferedImage canvas;

    public JRenderer() {
        window = DuuDuuEngine.ENGINE.getWindow();
        if (!isCompatibleToWindow()) throw new RuntimeException("JRenderer can't operate with incompatible window: " + window.getClass().getName());
        JWindow window = (JWindow) this.window;
        bs = window.getBufferStrategy();
        canvas = new BufferedImage(DuuDuuEngine.DEFAULT_RESOLUTION_WIDTH, DuuDuuEngine.DEFAULT_RESOLUTION_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void prepare() {
        g = canvas.getGraphics();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public boolean isCompatibleToWindow() {
        return window instanceof JWindow;
    }

    @Override
    public void drawRectangle(int hexColor, int x, int y, int width, int height) {
        Color color = new Color(hexColor, true);
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void fillRectangle(int hexColor, int x, int y, int width, int height) {
        Color color = new Color(hexColor, true);
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void drawTexture(Texture texture, int x, int y, int width, int height) {
        g.drawImage(texture.getImage(), x, y, width, height, null);
    }

    @Override
    public void drawTexture(Texture texture, Vector2 pos, Vector2 size) {
        drawTexture(texture, (int) pos.x, (int) pos.y, (int) size.x, (int) size.y);
    }

    @Override
    public void show() {
        g.dispose();

        Graphics screenG = bs.getDrawGraphics();
        screenG.clearRect(0, 0, DuuDuuEngine.ENGINE.WINDOW_WIDTH(), DuuDuuEngine.ENGINE.WINDOW_HEIGHT());
        screenG.drawImage(canvas, 0, 0, DuuDuuEngine.ENGINE.WINDOW_WIDTH(), DuuDuuEngine.ENGINE.WINDOW_HEIGHT(), null);
        screenG.dispose();
        bs.show();
    }

}
