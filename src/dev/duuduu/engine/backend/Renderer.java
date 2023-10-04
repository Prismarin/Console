package dev.duuduu.engine.backend;

public abstract class Renderer {

    public abstract void prepare();

    public abstract boolean isCompatibleToWindow();

    public abstract void drawRectangle(int hexColor, int x, int y, int width, int height);

    public abstract void fillRectangle(int hexColor, int x, int y, int width, int height);

    public abstract void show();

}
