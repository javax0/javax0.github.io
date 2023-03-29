package com.javax0.blog.romans;


// snippet Romans
public interface Romans {
    String toRoman(int value);

    static void assertRange(int value) {
        if (value < 1 || value > 3999) {
            throw new IllegalArgumentException("Counter '%s' grew too big to be formatted as a roman numeral".formatted(value));
        }
    }
}
// end snippet