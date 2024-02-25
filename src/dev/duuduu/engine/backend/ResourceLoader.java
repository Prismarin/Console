package dev.duuduu.engine.backend;

import dev.duuduu.engine.Game;
import dev.duuduu.resources.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
    private final Class<? extends Game> clazz;

    public ResourceLoader(Class<? extends Game> clazz) {
        this.clazz = clazz;
    }

    public Texture loadTexture(String path) {
        try {
            URL url = clazz.getResource(path);
            assert url != null;
            BufferedImage img = ImageIO.read(url);
            return new Texture(path, img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}