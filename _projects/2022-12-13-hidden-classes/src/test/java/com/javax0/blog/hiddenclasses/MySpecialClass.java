/**
 * This file is here, so I can edit it as a Java source with the help of the IDE. It is copied to the multi-line
 * string in the test files.
 */
package com.javax0.blog.hiddenclasses;

public class MySpecialClass implements TestHiddenClassLoader.Hello {

    @Override
    public void hello() {
        System.out.println("Hello, from the hidden class.");
    }
}
