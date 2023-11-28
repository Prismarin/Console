package test;

import dev.duuduu.scripts.Script2D;

import java.awt.event.KeyEvent;

import static dev.duuduu.engine.DuuDuuEngine.ENGINE;

public class PlayerScript extends Script2D {

    public float speed = 100;

    @Override
    public void tick(double delta) {
        super.tick(delta);

        if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_W)) {
            position.y -= (float) (speed * delta);
        }
        if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_S)) {
            position.y += (float) (speed * delta);
        }
        if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_A)) {
            position.x -= (float) (speed * delta);
        }
        if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_D)) {
            position.x += (float) (speed * delta);
        }
    }

}
