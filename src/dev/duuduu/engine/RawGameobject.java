package dev.duuduu.engine;

import dev.duuduu.engine.backend.EngineEvent;
import dev.duuduu.engine.backend.Renderer;

public abstract class RawGameobject {

    @EngineEvent
    public abstract void tick(double delta);

    @EngineEvent
    public abstract void render(Renderer renderer);

    public boolean isRaw() {
        return true;
    }

}
