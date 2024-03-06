package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;
import dev.duuduu.engine.backend.Renderer;
import dev.duuduu.resources.TilemapData;

import java.util.ArrayList;

public class Tilemap extends Script2D {
    public ArrayList<Tile> atlas;
    public TilemapData tilemapData;

    public Tilemap(GameObject gameObject) {
        super(gameObject);
        atlas = new ArrayList<>();
    }

    public void tick(double delta) {
        for (int i = 0, len = atlas.size(); i < len; i ++) {
            atlas.get(i).tick(delta);
        }
    }

    public void render(Renderer renderer) {
        if (tilemapData != null) tilemapData.renderTilemapData(renderer, atlas);
    }
}
