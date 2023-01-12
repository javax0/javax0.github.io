package com.javax0.blog.tenjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.javax0.blog.tenjava.Setout.resetout;
import static com.javax0.blog.tenjava.Setout.setout;

public class ArrayListTest {

    @Test
    void testArrayListOf() throws Exception {
        setout("ARRAYLIST.ERR");
        // snippet ArrayListCastError
        try {
            final ArrayList<Integer> x = (ArrayList) Arrays.asList(1, 2, 3, 4);
        } catch (ClassCastException cce) {
            System.out.println(cce);
        }
        // end snippet
        resetout();
    }

    @Test
    void testBackingArray() throws Exception {
        //snippet backingArray
        final Integer[] w = {1, 2, 3};
        final var wl = Arrays.asList(w);
        Assertions.assertEquals(1, wl.get(0));
        w[0] = 55;
        wl.set(1, 66);
        Assertions.assertEquals(55, wl.get(0));
        Assertions.assertEquals(66, w[1]);
        //end snippet

    }

}
