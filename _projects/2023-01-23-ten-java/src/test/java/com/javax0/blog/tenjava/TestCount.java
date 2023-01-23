package com.javax0.blog.tenjava;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import static com.javax0.blog.tenjava.Setout.resetout;
import static com.javax0.blog.tenjava.Setout.setout;

public class TestCount {

    @Test
    void testCountNoPeek() throws FileNotFoundException {
        setout("NO_PEEK.TXT");
        // snippet no_peeking
        final long z = Stream.of(1, 2, 3, 4, 5, "apple", "bird", 3.1415926)
                .peek(System.out::println).count();
        System.out.println(z);
        // end snippet
        resetout();
    }

    @Test
    void testCountPeek() throws FileNotFoundException {
        setout("PEEK.TXT");
        // snippet peeking
        final var w = Stream.of(1, 2, 3, 4, 5, "apple", "bird", 3.1415926)
                .peek(System.out::println).toArray();
        System.out.println(w.length);
        // end snippet
        resetout();
    }
}
