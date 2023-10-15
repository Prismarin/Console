package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Script;
import dev.duuduu.engine.Transform;

public class Script2D extends Script {

    public Transform transform;

    public Script2D(GameObject gameObject) {
        super(gameObject);
        this.transform = new Transform();
    }

}
