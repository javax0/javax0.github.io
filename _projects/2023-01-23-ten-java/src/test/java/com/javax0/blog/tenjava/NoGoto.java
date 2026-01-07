package com.javax0.blog.tenjava;

import com.javax0.sourcebuddy.Compiler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoGoto {

    @Test
    void noGoto() throws Exception {
        // snippet noGoto
        Compiler.java().from("""
                package com.javax0.blog.tenjava;
                class NoGotoPlease{
                  public void _goto(){
                  }
                }
                """
        ).compile();
        Assertions.assertThrows(Compiler.CompileException.class, () -> Compiler.java().from("""
                package com.javax0.blog.tenjava;
                class NoGotoPlease{
                  public void goto(){
                  }
                }
                """
        ).compile());
        // end snippet
    }
}
