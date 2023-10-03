package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;

public abstract class RawGameobject {

    public abstract void tick(float delta);

    public abstract void render(Renderer renderer);

}
