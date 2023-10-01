package dev.duuduu.console.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class EntryPoint {

    public static void main(String[] args) {
        System.out.println("Gathering starting info...");
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));

        DuuDuuEngine.ENGINE.initializeDebug(arguments.contains("DEBUG"));
    }

}
