package com.javax0.blog.tenjava;

import com.javax0.sourcebuddy.Compiler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStaticFinalMethod {


    @Test
    void testStaticFinalMethod() throws Exception {
        // snippet staticFinalMethod
        final var FIZZ = """
                package com.javax0.blog.tenjava;
                public class Fizz {
                    static void fizzy(){
                    }
                }
                """;
        final var BUZZ = """
                package com.javax0.blog.tenjava;
                public class Buzz extends Fizz {
                    static void fizzy(){
                    }
                }
                """;
        final var testSet1 = Compiler.java().from(
                FIZZ
        ).from(BUZZ).compile().load();
        testSet1.get("Buzz");
        Assertions.assertThrows(Compiler.CompileException.class, () -> Compiler.java().from(
                FIZZ.replaceAll("static void", "final static void")
        ).from(BUZZ).compile());
        // end snippet
    }

}
