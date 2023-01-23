package com.javax0.blog.tenjava;

import org.junit.jupiter.api.Test;

import static com.javax0.blog.tenjava.Setout.resetout;
import static com.javax0.blog.tenjava.Setout.setout;

public class BreakLabel {


    @Test
    void breakOutOfLoop() throws Exception {
        setout("LABEL_OUTPUT.TXT");
        // snippet LABEL
        LABEL1:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * j > 15 && (i * j) % 2 == 0) break LABEL1;
                System.out.print("%d,%d ".formatted(i, j));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        LABEL2:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * j > 15 && (i * j) % 2 == 0) {
                    System.out.println();
                    continue LABEL2;
                }
                System.out.print("%d,%d ".formatted(i, j));
            }
            System.out.println();
        }
        //end snippet
        resetout();
    }

    @Test
    void breakOutOfLoopNoLabel() throws Exception {
        setout("NO_LABEL_OUTPUT.TXT");
        // snippet noLABEL
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * j > 15 && (i * j) % 2 == 0) break;
                System.out.print("%d,%d ".formatted(i, j));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * j > 15 && (i * j) % 2 == 0) {
                    System.out.println();
                    continue;
                }
                System.out.print("%d,%d ".formatted(i, j));
            }
            System.out.println();
        }
        //end snippet
        resetout();
    }

}
