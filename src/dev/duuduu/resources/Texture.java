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
}
