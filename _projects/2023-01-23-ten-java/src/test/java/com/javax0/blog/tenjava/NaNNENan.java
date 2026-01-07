package com.javax0.blog.tenjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NaNNENan {

    @Test
    void testNaN(){
        //snippet NaN
        final var variable = Float.NaN;
        if( variable == variable ){
            Assertions.fail("variable is == to variable");
        }
        // end snippet
    }

}
