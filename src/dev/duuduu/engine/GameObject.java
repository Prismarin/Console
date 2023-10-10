package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public final class GameObject extends RawGameobject {

    private String name;
    private Script script;
    private final ArrayList<GameObject> children;
    private GameObject parent;

    public GameObject(GameObject parent, String name) {
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public void setParent(GameObject gameObject) {
        this.parent = gameObject;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void start() {
        if (script != null) script.start();
    }

    @Override
    public void tick(float delta) {
        if (script != null) script.tick(delta);
        for (int i = 0, len = children.size(); i < len; i ++) {
            children.get(i).tick(delta);
        }
    }

    @Override
    public void render(Renderer renderer) {
        if (script != null) script.render(renderer);
        for (int i = 0, len = children.size(); i < len; i ++) {
            children.get(i).render(renderer);
        }
    }

    public void addChild(@NotNull GameObject gameObject) {
        gameObject.start();
        children.add(gameObject);
    }

    public int getChildCount() {
        return children.size();
    }

    public GameObject getChild(int index) {
        return children.get(index);
    }

    public @Nullable GameObject getChild(String name) {
        GameObject gameObject;
        for (int i = 0, len = children.size(); i < len; i ++) {
            gameObject = children.get(i);
            if (gameObject.name.equals(name)) return gameObject;
        }
        return null;
    }

    public ArrayList<GameObject> getChildren() {
        return (ArrayList<GameObject>) children.clone();
    }

    public boolean setName(String name) {
        if (parent == null) {
            this.name = name;
            return true;
        }
        ArrayList<GameObject> siblings = parent.getChildren();
        GameObject gameObject;
        for (int i = 0, len = siblings.size(); i < len; i ++) {
            gameObject = siblings.get(i);
            if (gameObject.name.equals(name)) return false;
        }
        this.name = name;
        return true;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isRaw() {
        return false;
    }

}
