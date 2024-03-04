package dev.duuduu.resources;

import dev.duuduu.scripts.Timer;
import org.jetbrains.annotations.NotNull;

public class Animation {
    public Texture[] textures;
    public final int fps;
    public final Timer timer;
    public int frame;

    public Animation(int fps, Texture @NotNull [] textures) {
        assert textures.length > 0 && fps > 0;
        this.textures = textures;
        this.fps = fps;
        this.frame = 0;
        this.timer = new Timer();
        this.timer.autoStart = true;
        this.timer.waitTime = Timer.fromFpsToWaitTime(fps);
        this.timer.setRunnable(() -> {
            frame ++;
            if (frame >= textures.length) frame = 0;
        });
        this.timer.start();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void setFps(int fps) {
        try {
            this.getClass().getDeclaredField("fps").set(this, fps);
            this.timer.waitTime = Timer.fromFpsToWaitTime(fps);
            this.timer.time = this.timer.time / this.timer.waitTime;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Texture getAnimationFrame() {
        return textures[frame];
    }

    public int frameCount() {
        return textures.length;
    }

    public Texture getFrame(int frame) {
        return textures[frame];
    }

    public void tick(double delta) {
        this.timer.tick(delta);
    }
}