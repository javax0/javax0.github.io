package com.javax0.blog.romans;

// snippet RomansSimple
public class RomansSimple implements Romans {
    int [] places = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String [] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String toRoman(int value) {
        Romans.assertRange(value);
        final var s = new StringBuilder();
        for( int i = 0; i < places.length; i++ ) {
            while( value >= places[i] ) {
                s.append(numerals[i]);
                value -= places[i];
            }
        }
        return s.toString();
    }
}
// end snippet