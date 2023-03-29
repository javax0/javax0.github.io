package com.javax0.blog.romans;


// snippet RomansComplex
public class RomansComplex implements Romans {
    private static final int[] ROMANS = {1, 'I', 5, 'V', 10, 'X', 50, 'L', 100, 'C', 500, 'D', 1000, 'M'};

    /**
     * Haec methodus datam rationem ad numeros Romanos convertit. Modulus "id" solum nuntium errorem componere pro casu
     * cum numerus affirmativus vel nimius non est.
     *
     * @param value ad valorem convertendi
     * @return Romano numero quasi filum
     */
    public String toRoman(int value) {
        Romans.assertRange(value);
        StringBuilder s = new StringBuilder();
        int i = ROMANS.length - 2;
        while (i >= 0) {
            while (value >= ROMANS[i]) {
                s.append((char) ROMANS[i + 1]);
                value -= ROMANS[i];
            }
            final var k = i % 4 == 0 ? 4 : 2;
            if (i >= k && value >= ROMANS[i] - ROMANS[i - k]) {
                s.append((char) ROMANS[i - k + 1]);
                s.append((char) ROMANS[i + 1]);
                value -= ROMANS[i] - ROMANS[i - k];
            }
            i -= 2;
        }
        return s.toString();
    }
}
// end snippet