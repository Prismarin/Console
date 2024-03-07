package dev.duuduu.resources;

import dev.duuduu.engine.Transform;
import dev.duuduu.engine.Vector2;
import dev.duuduu.engine.Vector3;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.scripts.Tile;

import java.util.ArrayList;
import java.util.Arrays;

public class TilemapData {
    public int tileWidth = 32, tileHeight = 32;
    /**
     * used when drawTilesTileSize = false
     */
    public int tileOffsetX = 0, tileOffsetY = 0;
    public boolean drawTilesTileSize = false;
    private int[] tiles;
    private final Vector2 upLeft, downRight;
    private int width, height;

    public TilemapData() {
        this.tiles = new int[0];
        this.upLeft = new Vector2(0,0);
        this.downRight = new Vector2(0, 0);
        this.width = 0;
        this.height = 0;
    }

    /**
     *
     * @param tileData x, y for position, z for tile
     */
    public void setTiles(Vector3[] tileData) {
        upLeft.x = tileData[0].x;
        upLeft.y = tileData[0].y;
        downRight.x = tileData[0].x;
        downRight.y = tileData[0].y;

        for (int i = 1; i < tileData.length; i ++) {
            if (tileData[i].x < upLeft.x) upLeft.x = tileData[i].x;
            else if (tileData[i].x > downRight.x) downRight.x = tileData[i].x;
            if (tileData[i].y < upLeft.y) upLeft.y = tileData[i].y;
            else if (tileData[i].y > downRight.y) downRight.y = tileData[i].y;
        }

        for (int i = 0; i < tileData.length; i ++) {
            tileData[i].x += upLeft.x;
            tileData[i].y += upLeft.y;
        }

        width = (int) (downRight.x - upLeft.x);
        height = (int) (downRight.y - upLeft.y);
        tiles = new int[width * height];

        Arrays.fill(tiles, -1);

        int i;
        for (Vector3 tileDataPiece : tileData) {
            i = (int) (tileDataPiece.y * width + tileDataPiece.x);
            if (i < tiles.length) tiles[i] = (int) tileDataPiece.z;
        }
    }

    public void createTiles(Vector2 upLeft, int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        this.upLeft.x = upLeft.x;
        this.upLeft.y = upLeft.y;
        this.downRight.x = upLeft.x + width;
        this.downRight.y = upLeft.y + height;
        Arrays.fill(tiles, -1);
    }

    public void renderTilemapData(Renderer renderer, ArrayList<Tile> atlas) {
        final int atlasSize = atlas.size();
        Transform transform = new Transform(new Vector2(upLeft.x * tileWidth - (drawTilesTileSize ? 0 : tileOffsetX), upLeft.y * tileHeight - (drawTilesTileSize ? 0 : tileOffsetY)),
                drawTilesTileSize ? new Vector2(tileWidth, tileHeight) : new Vector2(atlas.get(0).bake(0).getWidth(), atlas.get(0).bake(0).getHeight()));
        int currentTileID;
        for (int y = 0, i = 0; y < height; y ++) {
            for (int x = 0; x < width; x ++) {
                currentTileID = tiles[i];
                i ++;
                if (currentTileID == -1 || currentTileID >= atlasSize) { transform.pos.x += tileWidth; continue; }
                atlas.get(currentTileID).render(renderer, transform);
                transform.pos.x += tileWidth;
            }
            transform.pos.x = upLeft.x * tileWidth - (drawTilesTileSize ? 0 : tileOffsetX);
            transform.pos.y += tileHeight;
        }
    }

    public int getTile(int x, int y) {
        return tiles[y * width + x];
    }

    public int getTile(Vector2 vec) {
        return getTile((int) vec.x, (int) vec.y);
    }

    public void setTile(int x, int y, int tileID) {
        tiles[y * width + x] = tileID;
    }

    public void setTile(Vector2 vec, int tileID) {
        setTile((int) vec.x, (int) vec.y, tileID);
    }

    public void setTile(Vector3 vec) {
        setTile((int) vec.x, (int) vec.y, (int) vec.z);
    }

    public int[] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
