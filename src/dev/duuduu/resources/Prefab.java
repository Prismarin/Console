package dev.duuduu.resources;

import dev.duuduu.engine.GameObject;

public abstract class Prefab {
    public abstract GameObject build(String name, Object... objects);
}