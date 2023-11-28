package test;

import dev.duuduu.engine.Game;
import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.RawScene;

public class TestGame extends Game {

    @Override
    public void load(boolean consoleDevice) {
        windowTitle = "L";
    }

    @Override
    public RawScene getFirstScene() {
        return new TestScene("BigL", new GameObject(null, "DaContainer"));
    }

}
