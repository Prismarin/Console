package dev.duuduu.console.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class EntryPoint {

    /**
     *
     * @param args DEBUG CONSOLE --RESOLUTION [WIDTH = 1280] [HEIGHT = 720] --TPS [TPS = 60] --THREADS [MODE = multi]
     */
    public static void main(String[] args) {
        System.out.println("Gathering starting info...");
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));

        DuuDuuEngine.ENGINE.initializeDebug(arguments.contains("DEBUG"));

        if (arguments.contains("--RESOLUTION")) {
            final int resolutionIndex = arguments.indexOf("--RESOLUTION");
            final int width = Integer.parseInt(arguments.get(resolutionIndex + 1));
            final int height = Integer.parseInt(arguments.get(resolutionIndex + 2));
            DuuDuuEngine.ENGINE.setResolution(width, height);
        }
    }

}
