package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Script;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Timer extends Script {
    public boolean repeat;
    public boolean autoStart;
    private boolean running;
    private boolean firstStartCall;
    public double waitTime;
    public double time;
    private Runnable runnable;

    public Timer(GameObject gameObject) {
        super(gameObject);

        this.repeat = false;
        this.autoStart = true;
        this.running = false;
        this.firstStartCall = true;
        this.waitTime = 1.;
        this.time = 0.;
        this.runnable = () -> {};
    }

    public Timer() {
        this(null);
    }

    @Override
    public void start() {
        if (firstStartCall) {
            firstStartCall = false;
            running = autoStart;
        } else {
            running = true;
        }
    }

    @Override
    public void tick(double delta) {
        if (running) {
            time += delta;
            if (time >= waitTime) {
                runnable.run();
                time -= waitTime;
                if (!repeat) {
                    running = false;
                }
            }
        }
    }

    public void stop() {
        running = false;
    }

    public @NotNull Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(@Nullable Runnable runnable) {
        this.runnable = runnable;
        if (this.runnable == null) this.runnable = () -> {};
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * @param waitTime the time in seconds
     * @return the time in fps
     */
    public static double fromWaitTimeToFps(double waitTime) {
        assert waitTime != 0;
        return 1 / waitTime;
    }

    /**
     *
     * @param fps the time in fps
     * @return the time in waitTime
     */
    public static double fromFpsToWaitTime(double fps) {
        assert fps != 0;
        return 1 / fps;
    }
}