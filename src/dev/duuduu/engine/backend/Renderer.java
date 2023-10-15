package dev.duuduu.engine.backend;

import dev.duuduu.engine.Vector2;
import dev.duuduu.resources.Texture;

public abstract class Renderer {

    public abstract void prepare();

    public abstract boolean isCompatibleToWindow();

    public abstract void drawRectangle(int hexColor, int x, int y, int width, int height);

    public abstract void fillRectangle(int hexColor, int x, int y, int width, int height);

    public abstract void drawTexture(Texture texture, int x, int y, int width, int height);

    public abstract void drawTexture(Texture texture, Vector2 pos, Vector2 size);

    public abstract void show();

}
