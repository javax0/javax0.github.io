package com.javax0.blog.hiddenclasses;

import com.javax0.sourcebuddy.Compiler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;

public class TestHiddenClassLoader {

    public interface Hello {
        void hello();
    }

    public static byte[] load(final String name, final String code) throws Compiler.CompileException, IllegalAccessException {
        final var classByteArray = Compiler.java().from(name, code).compile().stream().toArray(byte[][]::new);
        return classByteArray[0];
    }

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

    @Test
    @DisplayName("Invoke the hidden class and say hello")
    void sayHello() throws Exception {
        // snippet sayHello
        final var byteCode = load(CLASS_NAME, CODE1);
        final var lookup = MethodHandles.lookup();
        final var classLookup =
                lookup.defineHiddenClass(byteCode, true, MethodHandles.Lookup.ClassOption.NESTMATE);
        final var helloClass = (Class<Hello>) classLookup.lookupClass();

        helloClass.getConstructor().newInstance().hello();
        //end snippet
    }

    @Test
    @DisplayName("Invoke multiple hidden classes and say hello")
    void sayHelloTwice() throws Exception {
        // snippet sayHelloTwice
        final var byteCode1 = load(CLASS_NAME, CODE1);
        final var byteCode2 = load(CLASS_NAME, CODE2);
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
        final var hello = Compiler.java().from(CLASS_NAME, CODE1).compile().loadHidden().newInstance(CLASS_NAME, Hello.class);
        hello.hello();
        //end snippet
    }
}
