package test;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Vector2;
import dev.duuduu.resources.Prefab;
import dev.duuduu.scripts.ColoredRect;

public class PlayerPrefab extends Prefab {

    @Override
    public GameObject build(String name, Object... args) {
        GameObject root = new GameObject(null, name);
        PlayerScript playerScript = new PlayerScript();
        Vector2 pos = (Vector2) args[0];
        playerScript.position.x = pos.x;
        playerScript.position.y = pos.y;
        root.setScript(playerScript);

        GameObject c1 = new GameObject(root, "ColorRect");
        ColoredRect rectScript = new ColoredRect(0xFF20B2AA, 0, 0, 40, 40);
        rectScript.fill = true;
        rectScript.syncParentPos = true;
        c1.setScript(rectScript);

        root.addChild(c1);
        return root;
    }

}
