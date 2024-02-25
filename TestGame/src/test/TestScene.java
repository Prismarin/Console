package test;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Scene;
import dev.duuduu.engine.Transform;
import dev.duuduu.engine.Vector2;
import dev.duuduu.scripts.ColoredRect;

public class TestScene extends Scene {

    public TestScene(String name, GameObject root) {
        super(name, root);

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

        PlayerPrefab playerPrefab = new PlayerPrefab();
        GameObject player = playerPrefab.build("Player", new Vector2(400, 400));

        root.addChildren(player, g1, g2, g3);
    }

}
