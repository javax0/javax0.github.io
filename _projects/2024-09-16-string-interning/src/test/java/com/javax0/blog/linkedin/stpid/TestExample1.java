package com.javax0.blog.linkedin.stpid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class TestExample1 {

    @Test
    // snippet TestExample0
    void testForEachLoop0() {
        int[] array = {0, 0, 0, 0, 0, 0};
        for (int a : array) {
            a = 55; // does nothing
        }
        Assertions.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, array);
    }
    // end snippet

    @Test
    // snippet TestExample1
    void testForEachLoop1() {
        class X {
            int value;

            X(int value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                return ((X) o).value == value;
            }
        }
        final var array = new X[]{new X(0), new X(0), new X(0)};
        for (final var a : array) {
            a.value = 55;
        }
        Assertions.assertArrayEquals(new X[]{new X(55), new X(55), new X(55)}, array);
    }
    // end snippet

    @Test
    // snippet TestExample2
    void testForEachLoop() {
        int[] array = {0, 0, 0, 0, 0, 0};
        for (int[] j : IntStream.range(0, array.length).mapToObj(i -> array).toArray(int[][]::new)) {
            j[j[0]] = j[0];
            j[0]++;
        }
        Assertions.assertArrayEquals(new int[]{6, 1, 2, 3, 4, 5}, array);
    }
    // end snippet

}
