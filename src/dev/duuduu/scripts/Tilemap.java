package dev.duuduu.scripts;

import dev.duuduu.engine.GameObject;

import java.util.ArrayList;

public class Tilemap extends Script2D {
    private ArrayList<Tile> atlas;

    public Tilemap(GameObject gameObject) {
        super(gameObject);
    }
}
