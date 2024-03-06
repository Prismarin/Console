package test;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.Scene;
import dev.duuduu.engine.Vector3;
import dev.duuduu.resources.Texture;
import dev.duuduu.resources.TilemapData;
import dev.duuduu.scripts.ColoredRect;
import dev.duuduu.scripts.Tile;
import dev.duuduu.scripts.Tilemap;

import static dev.duuduu.engine.DuuDuuEngine.ENGINE;

public class SnakeScene extends Scene {
    public SnakeScene() {
        super("snake", new GameObject(null, "root"));

        Texture snakeTexture = ENGINE.LOAD_TEXTURE("snake.png");
        Texture apple = ENGINE.LOAD_TEXTURE("apple.png");

        GameObject backGround = new GameObject(getRoot(), "bg");
        backGround.setScript(new ColoredRect());
        backGround.getScript(ColoredRect.class).fill = true;
        backGround.getScript(ColoredRect.class).color = 0xFF8F8F8F;
        backGround.getScript(ColoredRect.class).transform.pos.x = 0;
        backGround.getScript(ColoredRect.class).transform.pos.y = 0;
        backGround.getScript(ColoredRect.class).transform.size.x = ENGINE.RESOLUTION_WIDTH();
        backGround.getScript(ColoredRect.class).transform.size.y = ENGINE.RESOLUTION_HEIGHT();

        GameObject world = new GameObject(getRoot(), "tilemap");
        world.setScript(new Tilemap(world));
        world.getScript(Tilemap.class).atlas.add(Tile.createStaticTile(snakeTexture));
        world.getScript(Tilemap.class).atlas.add(Tile.createStaticTile(apple));
        Vector3[] tileData = new Vector3[2];
        tileData[0] = new Vector3(0, 0, -1);
        tileData[1] = new Vector3(ENGINE.RESOLUTION_WIDTH() / 20.f, ENGINE.RESOLUTION_HEIGHT() / 20.f, -1);
        TilemapData tmd = new TilemapData();
        tmd.setTiles(tileData);
        tmd.drawTilesTileSize = true;
        tmd.tileWidth = 20;
        tmd.tileHeight = 20;
        world.getScript(Tilemap.class).tilemapData = tmd;

        GameObject snake = new GameObject(getRoot(), "snake");
        snake.setScript(new Snake(tmd));

        getRoot().addChildren(backGround, world, snake);
    }
}
