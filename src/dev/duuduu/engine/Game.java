package dev.duuduu.engine;

public abstract class Game {

    public String windowTitle = "Game";

    public abstract void load();

    public abstract RawScene getFirstScene();

}
