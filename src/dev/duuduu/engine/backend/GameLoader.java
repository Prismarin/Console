package dev.duuduu.engine.backend;

import dev.duuduu.engine.Game;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public final class GameLoader {

    @NotGarbage
    private URLClassLoader classLoader;

    public @NotNull Game loadGame() {
        final File dir = new File("game");
        if (!dir.exists()) throw new RuntimeException("game folder does not exist, no game to load!");
        File gameFile = getGameFile(dir);
        try (JarFile gameJar = new JarFile(gameFile)) {
            Manifest manifest = gameJar.getManifest();
            String gameClassPath = manifest.getMainAttributes().getValue("GameMain");
            try {
                classLoader = new URLClassLoader(new URL[] {gameFile.toURI().toURL()}, this.getClass().getClassLoader());
                Class<Game> main = (Class<Game>) Class.forName(gameClassPath, true, classLoader);
                return (Game) main.getConstructors()[0].newInstance();
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull File getGameFile(@NotNull File dir) {
        final File[] gameFiles = dir.listFiles();
        assert gameFiles != null;
        if (gameFiles.length < 1) throw new RuntimeException("game folder empty.");
        File gameFile = null;
        for (File file : gameFiles) {
            if (file.getPath().endsWith(".jar")) {
                if (gameFile == null) gameFile = file;
                else throw new RuntimeException("More than one jar file in game folder.");
            }
        }
        if (gameFile == null) throw new RuntimeException("No game file found.");
        return gameFile;
    }

}
