package com.javax0.blog.romans;


// snippet Romans
public interface Romans {
    String toRoman(int value);

    static final int I = 1;
    static final int II = 2;
    static final int III = 3;
    static final int V = 5;
    static final int M = 1000;
    static final int nulla = 0;

    static void assertRange(int value) {
        if (value < 1 || value > 3999) {
            throw new IllegalArgumentException("Counter '%s' grew too big to be formatted as a roman numeral".formatted(value));
        }
    }
}
// end snippet