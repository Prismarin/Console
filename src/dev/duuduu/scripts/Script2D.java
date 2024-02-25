package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Script;
import dev.duuduu.engine.Transform;
import dev.duuduu.engine.Vector2;

public class Script2D extends Script {
    public Transform transform;

    public final Vector2 position;
    public final Vector2 size;
    public final Vector2 scale;

    public boolean syncParentPos = false;

    public Script2D(GameObject gameObject) {
        super(gameObject);
        this.transform = new Transform();

        this.position = this.transform.position;
        this.size = this.transform.size;
        this.scale = this.transform.scale;
    }

    public Script2D() {
        this(null);
    }

    public void syncParentPosition() {
        GameObject parent = gameObject.getParent();
        if (parent.hasScript()) {
            Script2D parentScript = parent.getScript(Script2D.class);
            this.position.x = parentScript.position.x;
            this.position.y = parentScript.position.y;
        }
    }

    @Override
    public void tick(double delta) {
        if (syncParentPos) syncParentPosition();
    }
}