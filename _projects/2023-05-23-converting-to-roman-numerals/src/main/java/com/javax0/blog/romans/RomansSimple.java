package com.javax0.blog.romans;

// snippet RomansSimple
public class RomansSimple implements Romans {
    int [] places = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String [] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String toRoman(int value) {
        Romans.assertRange(value);
        final var s = new StringBuilder();
        for( int j = 0; j < places.length; j++ ) {
            while( value >= places[j] ) {
                s.append(numerals[j]);
                value -= places[j];
            }
        }
        return s.toString();
    }
}
// end snippet