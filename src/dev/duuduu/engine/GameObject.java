package dev.duuduu.engine;

import dev.duuduu.engine.backend.Renderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public final class GameObject extends RawGameobject {

    private boolean queuedToRemove;
    private String name;
    private Script script;
    private final ArrayList<GameObject> children;
    private GameObject parent;
    private boolean running;

    public GameObject(GameObject parent, String name) {
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
        running = false;
    }

    public void setParent(GameObject gameObject) {
        this.parent = gameObject;
    }

    public void setScript(Script script) {
        this.script = script;
        script.setGameObject(this);
    }

    public void start() {
        if (script != null) script.start();
        for (int i = 0, len = children.size(); i < len; i ++) {
            children.get(i).start();
        }
        running = true;
    }

    @Override
    public void tick(float delta) {
        if (script != null) script.tick(delta);
        if (children.isEmpty()) return;
        for (int i = 0, len = children.size(); i < len; i ++) {
            children.get(i).tick(delta);
        }
        GameObject gameObject;
        for (int i = 0; i < children.size(); i ++) {
            gameObject = children.get(i);
            if (gameObject.queuedToRemove) {
                children.remove(i);
                i --;
            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        if (script != null) script.render(renderer);
        for (int i = 0; i < children.size(); i ++) {
            children.get(i).render(renderer);
        }
    }

    public GameObject getParent() {
        return parent;
    }

    public void addChild(@NotNull GameObject gameObject) {
        if (running) gameObject.start();
        children.add(gameObject);
        String name = gameObject.name;
        if (gameObject.name.isEmpty()) gameObject.name = "" + gameObject.hashCode();
        int iterator = 1;
        while (!gameObject.setName(name)) {
            name = gameObject.name + iterator;
        }
        if (gameObject.parent != this) gameObject.setParent(this);
        if (!gameObject.running && running) gameObject.start();
    }

    public void addChildren(@NotNull GameObject... gameObjects) {
        for (GameObject gameObject : gameObjects) {
            addChild(gameObject);
        }
    }

    public int getChildCount() {
        return children.size();
    }

    public GameObject getChild(int index) {
        if (children.size() > index) return children.get(index);
        System.err.printf("Caught: ArrayIndexOutOfBoundsException! index %d is not in children.size()!\n", index);
        return null;
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

    public void removeChild(int index) {
        if (children.size() > index) children.get(index).queuedToRemove = true;
        else System.err.printf("Caught: ArrayIndexOutOfBoundsException! index %d is not in children.size()!\n", index);
    }

    public void removeChild(String name) {
        GameObject child = getChild(name);
        if (child != null) child.queuedToRemove = true;
        else System.err.printf("Child with name: \"%s\" not found\n", name);
    }

    public void queueFree() {
        queuedToRemove = true;
        if (parent == null) DuuDuuEngine.ENGINE.EXIT();
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

    public boolean hasScript() {
        return script != null;
    }

    public <T extends Script> T getScript(Class<T> tClass) {
        return (T) script;
    }

    @Override
    public boolean isRaw() {
        return false;
    }

    public GameObject getRoot() {
        return DuuDuuEngine.ENGINE.getRoot();
    }

}
