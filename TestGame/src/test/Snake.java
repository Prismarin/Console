package test;

import dev.duuduu.engine.Vector2;
import dev.duuduu.resources.TilemapData;
import dev.duuduu.scripts.Script2D;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import static dev.duuduu.engine.DuuDuuEngine.ENGINE;

public class Snake extends Script2D {
    private final double tickTime = 0.16d;
    private double time = 0.0d;
    private final TilemapData world;
    private Vector2 direction = new Vector2(0, -1);
    private Vector2 apple;
    private final Random rand;
    private final int worldWidth, worldHeight;
    private final ArrayList<Vector2> snake;

    public Snake(TilemapData tmd) {
        this.world = tmd;
        this.worldWidth = tmd.getWidth();
        this.worldHeight = tmd.getHeight();
        this.rand = new Random();

        snake = new ArrayList<>();
        snake.add(new Vector2(worldWidth >> 1, worldHeight >> 1));
        snake.add(new Vector2(worldWidth >> 1, (worldHeight >> 1) + 1));
        snake.add(new Vector2(worldWidth >> 1, (worldHeight >> 1) + 2));
        snake.add(new Vector2(worldWidth >> 1, (worldHeight >> 1) + 3));
        for (int i = 0; i < snake.size(); i ++) {
            tmd.setTile(snake.get(i), 0);
        }
        apple = new Vector2(rand.nextInt(worldWidth), rand.nextInt(worldHeight));
        tmd.setTile(apple, 1);
    }

    @Override
    public void tick(double delta) {
        super.tick(delta);

        time += delta;
        if (time > tickTime) {
            time -= tickTime;
            Vector2 add = new Vector2(snake.get(0).x + direction.x, snake.get(0).y + direction.y);
            if (add.equals(apple)) {
                do {
                    apple.x = rand.nextInt(worldWidth);
                    apple.y = rand.nextInt(worldHeight);
                } while (snake.contains(apple));
                world.setTile(apple, 1);
            } else {
                Vector2 remove = snake.remove(snake.size() - 1);
                world.setTile(remove, -1);
            }
            if (snake.contains(add) || add.x < 0 || add.x >= worldWidth || add.y < 0 || add.y >= worldHeight) {
                time = -999999999999d;
            } else {
                snake.add(0, add);
                world.setTile(add, 0);
            }
        }

        if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_W)) { direction.x = 0; direction.y = -1; }
        else if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_S)) { direction.x = 0; direction.y = 1; }
        if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_A)) { direction.x = -1; direction.y = 0; }
        else if (ENGINE.IS_KEY_PRESSED(KeyEvent.VK_D)) { direction.x = 1; direction.y = 0; }
    }
}
