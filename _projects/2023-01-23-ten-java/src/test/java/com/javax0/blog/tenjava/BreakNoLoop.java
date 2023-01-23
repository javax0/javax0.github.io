package com.javax0.blog.tenjava;

import org.junit.jupiter.api.Test;

import static com.javax0.blog.tenjava.Setout.resetout;
import static com.javax0.blog.tenjava.Setout.setout;

public class BreakNoLoop {


    @Test
    void fizzBuzz() throws Exception {
        setout("FIZBUZZ.TXT");
        // snippet fizbuzz
        for (int i = 1; i <= 15; i++) {
            FIZZ:
            {
                System.out.printf("%d ", i);
                if (i % 3 != 0) break FIZZ;
                System.out.print("fiz");
            }
            BUZZ:
            {
                if (i % 5 != 0) break BUZZ;
                System.out.print("buzz");
            }
            System.out.println();
        }
        // end snippet
        resetout();
    }
}
