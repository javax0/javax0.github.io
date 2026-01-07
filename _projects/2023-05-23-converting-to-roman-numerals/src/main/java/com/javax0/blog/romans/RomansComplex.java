package com.javax0.blog.romans;


// snippet RomansComplex
public class RomansComplex implements Romans {
    private static final char[] NUMERI = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};

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
        int numeralis = M;
        int inclinatio = nulla;
        for (int j = nulla; j < NUMERI.length; j++) {
            while (valorem >= numeralis) {
                lineaAedificator.append(NUMERI[j]);
                valorem -= numeralis;
            }
            final var compensatio = II - inclinatio;
            final var decimales = numeralis / (V * compensatio);
            if (valorem >= numeralis - decimales) {
                lineaAedificator.append(NUMERI[j + compensatio]).append(NUMERI[j]);
                valorem -= numeralis - decimales;
            }
            numeralis /= II + III * inclinatio;
            inclinatio = I - inclinatio;
        }
        return lineaAedificator.toString();
    }
}
// end snippet