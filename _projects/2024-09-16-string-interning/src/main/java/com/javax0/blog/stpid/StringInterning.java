package com.javax0.blog.stpid;

// snippet StringInterning
public class StringInterning {
    static final String staticFinalString1 = "Hello";
    static final String staticFinalString2 = staticFinalString1 + " World";
    static final String staticFinalString3 = "Hello World";
    static String staticString1 = "Hello";
    static String staticString2 = staticString1 + " World";
    static String staticString3 = "Hello World";
    final String finalString1 = "Hello";
    final String finalString2 = finalString1 + " World";
    final String finalString3 = "Hello World";
    String string1 = "Hello";
    String string2 = string1 + " World";
    String string3 = "Hello World";

    public static void main(String[] args) {
        final String localFinalString1 = "Hello";
        final String localFinalString2 = localFinalString1 + " World";
        final String localFinalString3 = "Hello World";
        String localString1 = "Hello";
        String localString2 = localString1 + " World";
        String localString3 = "Hello World";

        System.out.println("staticFinalString2 == staticFinalString3: " + (staticFinalString2 == staticFinalString3));
        System.out.println("staticString2 == staticString3: " + (staticString2 == staticString3));
        System.out.println("finalString2 == finalString3: " + (new StringInterning().finalString2 == new StringInterning().finalString3));
        System.out.println("string2 == string3: " + (new StringInterning().string2 == new StringInterning().string3));
        System.out.println("localFinalString2 == localFinalString3: " + (localFinalString2 == localFinalString3));
        System.out.println("localString2 == localString3: " + (localString2 == localString3));
        System.out.println("localString2.intern() == localString3.intern(): " + (localString2.intern() == localString3.intern()));
    }
}
// end snippet