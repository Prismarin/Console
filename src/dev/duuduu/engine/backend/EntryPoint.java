package dev.duuduu.engine.backend;

import dev.duuduu.console.ConsoleJInputSystem;
import dev.duuduu.engine.DuuDuuEngine;
import dev.duuduu.engine.RawScene;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class EntryPoint {

    /**
     *
     * @param args DEBUG CONSOLE --RESOLUTION [WIDTH = 1280] [HEIGHT = 720] --TPS [TPS = 60] --THREADS [MODE = multi] --LEGACY
     */
    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        long start = System.nanoTime();
        System.out.println("Gathering starting info...");
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));

        DuuDuuEngine.ENGINE.initializeDebug(arguments.contains("DEBUG"));

        if (arguments.contains("--RESOLUTION")) {
            final int resolutionIndex = arguments.indexOf("--RESOLUTION");
            final int width = Integer.parseInt(arguments.get(resolutionIndex + 1));
            final int height = Integer.parseInt(arguments.get(resolutionIndex + 2));
            DuuDuuEngine.ENGINE.SET_RESOLUTION(width, height);
        }

        if (arguments.contains("--TPS")) {
            final int tpsIndex = arguments.indexOf("--TPS");
            DuuDuuEngine.ENGINE.TPS = Integer.parseInt(arguments.get(tpsIndex + 1));
        }

        if (arguments.contains("--THREADS")) {
            final int threadModeIndex = arguments.indexOf("--THREADS");
            final String threadMode = arguments.get(threadModeIndex + 1);
            DuuDuuEngine.ENGINE.THREAD_MODE = ThreadMode.fromString(threadMode);
        } else {
            DuuDuuEngine.ENGINE.THREAD_MODE = ThreadMode.MULTI;
        }

        boolean jdkGraphics = arguments.contains("--LEGACY");

        // load engine here...
        System.out.println("Starting " + DuuDuuEngine.VERSION + "...");

        DuuDuuEngine.ENGINE.initWindow(JWindow.class);
        DuuDuuEngine.ENGINE.initSceneManager();
        DuuDuuEngine.ENGINE.initGameLoop();

        // either load console or game
        if (!arguments.contains("CONSOLE")) {
            // load game
            DuuDuuEngine.ENGINE.initInputSystem(JInputSystem.class);
        } else {
            // load console
            DuuDuuEngine.ENGINE.initInputSystem(ConsoleJInputSystem.class);
        }

        DuuDuuEngine.ENGINE.startGameLoop();
        System.out.printf("DuuDuuEngine started! %.0fms have passed.%n", ((System.nanoTime() - start) * 1E-6));
    }

}
