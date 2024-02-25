package dev.duuduu.engine.backend;

public enum ThreadMode {
    SINGLE, MULTI;

    public static ThreadMode fromString(String s) {
        if (s.equals("single")) return SINGLE;
        if (s.equals("multi")) return MULTI;
        return null;
    }
}
