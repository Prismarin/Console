package dev.duuduu.engine.backend;

import java.lang.reflect.InvocationTargetException;

public abstract class Window {

    public InputSystem inputSystem;

    public abstract void init(String... args);

    public abstract <T extends InputSystem> void setInputSystem(Class<T> inputSystemClazz)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    public abstract void startLoop();

    public abstract void loop();

    public abstract int getWidth();

    public abstract int getHeight();

}
