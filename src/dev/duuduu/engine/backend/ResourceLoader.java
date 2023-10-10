package dev.duuduu.engine.backend;

import dev.duuduu.engine.Game;
import dev.duuduu.resources.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {

    private final Class<Game> clazz;

    public ResourceLoader(Class<Game> clazz) {
        this.clazz = clazz;
    }

    public Texture loadTexture(String path) {
        try (InputStream in = clazz.getResourceAsStream(path)) {
            BufferedImage img = ImageIO.read(in);
            return new Texture(path, img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
