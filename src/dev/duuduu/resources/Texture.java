package dev.duuduu.resources;

import dev.duuduu.engine.Resource;

import java.awt.image.BufferedImage;

public class Texture extends Resource {
    protected BufferedImage image;

    public Texture(String path, BufferedImage image) {
        super(path);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Texture crop(int x, int y, int width, int height) {
        return new Texture(path, image.getSubimage(x, y, width, height));
    }

    public Texture[] splitSpriteSheet(int wCount, int hCount) {
        assert wCount > 0 && hCount > 0;
        final int width = getWidth() / wCount;
        final int height = getHeight() / hCount;
        Texture[] ret = new Texture[wCount * hCount];
        int i = 0;
        for (int x = 0; x < wCount; x ++) {
            for (int y = 0; y < hCount; y ++) {
                ret[i] = crop(x * width, y * height, width, height);
                i ++;
            }
        }
        return ret;
    }
}