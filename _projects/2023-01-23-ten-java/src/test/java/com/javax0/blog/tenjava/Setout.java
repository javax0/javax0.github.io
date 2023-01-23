package com.javax0.blog.tenjava;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Setout {

    private static PrintStream out;

    static void setout(final String output) throws FileNotFoundException {
        out = System.out;
        System.setOut(new PrintStream(output));
    }

    static void resetout() {
        System.setOut(out);
    }
}
