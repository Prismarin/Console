package dev.duuduu.engine.backend;

import dev.duuduu.engine.Game;
import dev.duuduu.resources.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {

    private final Class<? extends Game> clazz;

    public ResourceLoader(Class<? extends Game> clazz) {
        this.clazz = clazz;
    }

    public Texture loadTexture(String path) {
        try {
            InputStream in = clazz.getResourceAsStream(path);
            assert in != null;
            BufferedImage img = ImageIO.read(in);
            in.close();
            return new Texture(path, img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
