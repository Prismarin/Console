package dev.duuduu.engine.backend;

import dev.duuduu.engine.DuuDuuEngine;
import dev.duuduu.engine.JInputSystem;
import dev.duuduu.engine.JWindow;
import dev.duuduu.engine.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class EntryPoint {

    /**
     *
     * @param args DEBUG CONSOLE --RESOLUTION [WIDTH = 1280] [HEIGHT = 720] --TPS [TPS = 60] --THREADS [MODE = multi]
     */
    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
        }

        // load engine here...
        DuuDuuEngine.ENGINE.initWindow(JWindow.class);

        // either load console or game
        if (!arguments.contains("CONSOLE")) {
            // load game
            DuuDuuEngine.ENGINE.initInputSystem(JInputSystem.class);
        } else {
            // load console
        }

    }

}
