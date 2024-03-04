package dev.duuduu.resources;

import dev.duuduu.engine.Vector2;
import dev.duuduu.engine.Vector3;
import dev.duuduu.scripts.Tile;

import java.util.ArrayList;

public class TilemapData {
    public int tileWidth = 32, tileHeight = 32;
    public boolean drawTilesTileSize = false;
    public final int[] tiles;
    private final Vector2 upLeft;
    private int width, height;

    public TilemapData() {
        this.tiles = new int[0];
        this.upLeft = new Vector2(0,0);
        this.width = 0;
        this.height = 0;
    }

    /**
     *
     * @param tileData x, y for position, z for tile
     */
    public void setTiles(Vector3[] tileData) {

    }

    public void renderTilemapData(ArrayList<Tile> atlas) {

    }
}
