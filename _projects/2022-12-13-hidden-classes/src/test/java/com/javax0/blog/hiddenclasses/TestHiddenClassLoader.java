package com.javax0.blog.hiddenclasses;

import com.javax0.sourcebuddy.Compiler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;

// snipline TestHiddenClassLoader filter=(Test\w+)
public class TestHiddenClassLoader {

    // snippet InterfaceHello
    interface Hello {
        void hello();
    }
    // end snippet

    // snippet CODE1
    private static final String CODE1 = """
            package com.javax0.blog.hiddenclasses;
                        
            public class MySpecialClass implements TestHiddenClassLoader.Hello {
                        
                @Override
                public void hello() {
                    System.out.println("Hello, from the hidden class.");
                }
            }
            """;
    // end snippet

    // snipline CODE2
    private static final String CODE2 = CODE1.replaceAll("Hello, from the hidden class.", "Hello, from the other hidden class.");
    // snipline CLASS_NAME
    public static final String CLASS_NAME = "com.javax0.blog.hiddenclasses.MySpecialClass";

    private static PrintStream out;
    private static void setout(final String output) throws FileNotFoundException {
        out = System.out;
        System.setOut(new PrintStream(output));
    }
    private static void resetout(){
        System.setOut(out);
    }

    @Test
    @DisplayName("Invoke the hidden class and say hello")
    void sayHello() throws Exception {
        setout("hello_parameters.txt");
        // snippet sayHello
        final var byteCode = Compiler.java().from(CODE1).compile().get();
        final var lookup = MethodHandles.lookup();
        final var classLookup = lookup.defineHiddenClass(byteCode, true);
        final var helloClass = (Class<Hello>) classLookup.lookupClass();

        final var hello = helloClass.getConstructor().newInstance();
        hello.hello();
        //end snippet
        // snippet hidden_parameters
        System.out.println(hello.getClass());
        System.out.println(hello.getClass().getSimpleName());
        System.out.println(hello.getClass().getName());
        System.out.println(hello.getClass().getCanonicalName());
        // end snippet
        resetout();
    }

    @Test
    @DisplayName("Load a hidden class initialized vs. one not initialized")
    void initVsNonInit() throws Exception {
        // snippet initVsNonInit
        final var CODE =
                """
                        package com.javax0.blog.hiddenclasses;
                        public class A {
                            static {
                              System.out.println("Class A is initialized");
                            }
                        }
                        """;
        final var byteCodeA = Compiler.java().from(CODE).compile().get();
        final var byteCodeB = Compiler.java().from(CODE.replaceAll("A","B")).compile().get();
        final var lookup = MethodHandles.lookup();
        lookup.defineHiddenClass(byteCodeA, true);
        lookup.defineHiddenClass(byteCodeB, false);
        //end snippet
    }

    @Test
    @DisplayName("Invoke multiple hidden classes and say hello")
    void sayHelloTwice() throws Exception {
        // snippet sayHelloTwice
        final var byteCode1 = Compiler.java().from(CODE1).compile().get();
        final var byteCode2 = Compiler.java().from(CODE2).compile().get();
        final var lookup1 = MethodHandles.lookup();
        final var classLookup1 =
                lookup1.defineHiddenClass(byteCode1, true, MethodHandles.Lookup.ClassOption.NESTMATE);
        final var helloClass1 = (Class<Hello>) classLookup1.lookupClass();
        final var lookup2 = MethodHandles.lookup();
        final var classLookup2 =
                lookup2.defineHiddenClass(byteCode2, true, MethodHandles.Lookup.ClassOption.NESTMATE);
        final var helloClass2 = (Class<Hello>) classLookup2.lookupClass();
        final var hello1 = helloClass1.getConstructor().newInstance();
        final var hello2 = helloClass2.getConstructor().newInstance();
        hello1.hello();
        hello2.hello();
        hello1.hello();
        hello2.hello();
        // end snippet
    }

    @Test
    @DisplayName("Invoke the hidden class and say hello using SourceBuddy")
    void sayHellotoSB() throws Exception {
        // snippet sayHelloSB
        final var hello = Compiler.java().from(CLASS_NAME, CODE1).hidden().compile().load().newInstance(CLASS_NAME, Hello.class);
        hello.hello();
        //end snippet
    }
}
