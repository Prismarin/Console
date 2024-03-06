package test;

import dev.duuduu.engine.Game;
import dev.duuduu.engine.RawScene;

public class TestGame extends Game {

    @Override
    public void load(boolean consoleDevice) {
        windowTitle = "BigL";
    }

    @Override
    public RawScene getFirstScene() {
        return new SnakeScene();
    }

}
