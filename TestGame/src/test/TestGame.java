package test;

import dev.duuduu.engine.*;
import dev.duuduu.scripts.ColoredRect;

public class TestGame extends Game {

    @Override
    public void load() {
        windowTitle = "L";
    }

    @Override
    public RawScene getFirstScene() {
        GameObject root = new GameObject(null, "DaContainer");

        GameObject g1 = new GameObject(root, "r1");
        g1.setScript(new ColoredRect());
        Transform transform1 = g1.getScript(ColoredRect.class).transform;
        transform1.pos.x = 100;
        transform1.pos.y = 100;
        transform1.size.x = 100;
        transform1.size.y = 100;
        g1.getScript(ColoredRect.class).color = 0xFF191970;

        GameObject g2 = new GameObject(root, "r2");
        g2.setScript(new ColoredRect(0xFFC71585, 200, 400, 50, 90));

        GameObject g3 = new GameObject(root, "r3");
        g3.setScript(new ColoredRect(0xFFFFA07A, 200, 100, 50, 50, 2, 2));

        root.addChildren(g1, g2, g3);

        return new TestScene("BigL", root);
    }

}
