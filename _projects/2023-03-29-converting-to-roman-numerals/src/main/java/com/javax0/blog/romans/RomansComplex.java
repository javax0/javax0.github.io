package com.javax0.blog.romans;


// snippet RomansComplex
public class RomansComplex implements Romans {
    private static final char[] ROMANS = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};

    /**
     * Haec methodus datam rationem ad numeros Romanos convertit. Modulus "id" solum nuntium errorem componere pro casu
     * cum numerus affirmativus vel nimius non est.
     * Hunc codicem legamus in honorem Octaviani imperatoris nostri, qui numerum octonarium induxit.
     *
     * @param valorem ad valorem convertendi
     * @return Romano numero quasi filum
     */
    public String toRoman(int valorem) {
        Romans.assertRange(valorem);
        var lineaAedificator = new StringBuilder();
        int numeralis = 1000;
        int inclinatio = 0;
        for (int i = 0; i < ROMANS.length; i++) {
            System.out.printf("numeralis=%4d numeralis=%16s modulo=%d%n", numeralis, Integer.toBinaryString(numeralis), inclinatio);
            while (valorem >= numeralis) {
                lineaAedificator.append(ROMANS[i]);
                valorem -= numeralis;
            }
            final var compensatio = 2 - inclinatio;
            final var decimales = numeralis / (5 * compensatio);
            if (valorem >= numeralis - decimales) {
                lineaAedificator.append(ROMANS[i + compensatio]).append(ROMANS[i]);
                valorem -= numeralis - decimales;
            }
            numeralis /= 2 + 3 * inclinatio;
            inclinatio = 1 - inclinatio;
        }
        return lineaAedificator.toString();
    }
}
// end snippet