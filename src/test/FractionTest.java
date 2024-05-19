import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private <T> void assertFractionEquals(T[] fractionAsArray, Fraction<T> fraction) {
        assertArrayEquals(fractionAsArray, fraction.asArray());
    }
    private <T> void assertSimplifiedFractionEquals(T[] fractionAsArray, Fraction<T> fraction) {
        assertArrayEquals(fractionAsArray, fraction.simplify().asArray());
    }

    private <T> void assertDeepArrayEquals(T[] arr1, T[] arr2) {
        assertTrue(Arrays.deepEquals(arr1, arr2));
    }
    @Test
    void toStringTest() {
        assertEquals("{3, 5}", new LongFraction(3, 5).toString());
        assertEquals("{3, 51234567898765432123456789}", new BigFraction(BigInteger.valueOf(3), new BigInteger("51234567898765432123456789")).toString());
    }
    @Test
    void createFraction() {
        assertThrows(IllegalArgumentException.class, () -> new LongFraction(0, 0));
        assertFractionEquals(new Long[] {0L, 1L}, new LongFraction(0, 1));
        assertFractionEquals(new Long[] {-1L, 1L}, new LongFraction(1, -1));
    }
    @Test
    void simplifyFraction() {
        LongFraction fraction = new LongFraction(1, 1);
        assertFractionEquals(new Long[] {1L, 1L}, fraction.simplify());

        fraction = new LongFraction(2, 2);
        assertFractionEquals(new Long[] {1L, 1L}, fraction.simplify());

        fraction = new LongFraction(26, 8);
        assertFractionEquals(new Long[] {13L, 4L}, fraction.simplify());
    }
    @Test
    void addFraction() {
        LongFraction fraction1 = new LongFraction(1, 1);
        LongFraction fraction2 = new LongFraction(0, 1);
        LongFraction sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(new Long[] {1L, 1L}, sum);

        fraction1 = new LongFraction(1, 1);
        fraction2 = new LongFraction(1, 1);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(new Long[] {2L, 1L}, sum);

        fraction1 = new LongFraction(1, 2);
        fraction2 = new LongFraction(1, 3);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(new Long[] {5L, 6L}, sum);
    }
    @Test
    void subtractFraction() {
        LongFraction fraction1 = new LongFraction(1, 1);
        LongFraction fraction2 = new LongFraction(0, 1);
        LongFraction difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(new Long[] {1L, 1L}, difference);

        fraction1 = new LongFraction(0, 1);
        fraction2 = new LongFraction(1, 1);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(new Long[] {-1L, 1L}, difference);

        fraction1 = new LongFraction(1, 2);
        fraction2 = new LongFraction(1, 3);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(new Long[] {1L, 6L}, difference);
    }
    @Test
    void multiplyFraction() {
        LongFraction fraction1 = new LongFraction(1, 1);
        LongFraction fraction2 = new LongFraction(0, 1);
        LongFraction product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new Long[] {0L, 1L}, product);

        fraction1 = new LongFraction(0, 1);
        fraction2 = new LongFraction(1, 1);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new Long[] {0L, 1L}, product);

        fraction1 = new LongFraction(1, 3);
        fraction2 = new LongFraction(3, 1);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new Long[] {1L, 1L}, product);

        fraction1 = new LongFraction(1, 2);
        fraction2 = new LongFraction(1, 3);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new Long[] {1L, 6L}, product);
    }
    @Test
    void divideFraction() {
        LongFraction dividend = new LongFraction(1, 1);
        LongFraction divider = new LongFraction(0, 1);
        assertThrows(IllegalArgumentException.class, () -> dividend.divide(divider));

        LongFraction fraction1 = new LongFraction(1, 3);
        LongFraction fraction2 = new LongFraction(3, 1);
        LongFraction quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(new Long[] {1L, 9L}, quotient);

        fraction1 = new LongFraction(1, 2);
        fraction2 = new LongFraction(1, 3);
        quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(new Long[] {3L, 2L}, quotient);
    }
    @Test
    void getCycle() {
        LongFraction fraction = new LongFraction(1, 1);
        assertArrayEquals(new int[][] {}, fraction.getCycle());

        fraction = new LongFraction(3, 2);
        assertDeepArrayEquals(new int[][] {{5}, {}}, fraction.getCycle());

        fraction = new LongFraction(1, 3);
        assertDeepArrayEquals(new int[][] {{}, {3}}, fraction.getCycle());

        fraction = new LongFraction(100, 1001);
        assertDeepArrayEquals(new int[][] {{}, {0, 9, 9, 9, 0, 0}}, fraction.getCycle());

        BigFraction bigFraction = new BigFraction(BigInteger.valueOf(3), BigInteger.TWO);
        assertDeepArrayEquals(new int[][] {{5}, {}}, bigFraction.getCycle());

        bigFraction = new BigFraction(BigInteger.valueOf(1), BigInteger.valueOf(3));
        assertDeepArrayEquals(new int[][] {{}, {3}}, bigFraction.getCycle());

        bigFraction = new BigFraction(BigInteger.valueOf(100), BigInteger.valueOf(1001));
        assertDeepArrayEquals(new int[][] {{}, {0, 9, 9, 9, 0, 0}}, bigFraction.getCycle());
    }

    BigInteger[] bigArray(Long... longs) {
        BigInteger[] arr = new BigInteger[longs.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = BigInteger.valueOf(longs[i]);
        }

        return arr;
    }
    @Test
    void createBigFraction() {
        assertThrows(IllegalArgumentException.class, () -> new BigFraction(BigInteger.ZERO, BigInteger.ZERO));
        assertFractionEquals(bigArray(0L, 1L), new BigFraction(BigInteger.ZERO, BigInteger.ONE));
        assertFractionEquals(bigArray(-1L, 1L), new BigFraction(BigInteger.ONE, BigInteger.valueOf(-1)));
    }
    @Test
    void simplifyBigFraction() {
        BigFraction fraction = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        assertFractionEquals(bigArray(1L, 1L), fraction.simplify());

        fraction = new BigFraction(BigInteger.TWO, BigInteger.TWO);
        assertFractionEquals(bigArray(1L, 1L), fraction.simplify());

        fraction = new BigFraction(BigInteger.valueOf(26L), BigInteger.valueOf(8L));
        assertFractionEquals(bigArray(13L, 4L), fraction.simplify());
    }
    @Test
    void addBigFraction() {
        BigFraction fraction1 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        BigFraction fraction2 = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
        BigFraction sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(bigArray(1L, 1L), sum);

        fraction1 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(bigArray(2L, 1L), sum);

        fraction1 = new BigFraction(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.valueOf(3));
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(bigArray(5L, 6L), sum);
    }
    @Test
    void subtractBigFraction() {
        BigFraction fraction1 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        BigFraction fraction2 = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
        BigFraction difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(bigArray(1L, 1L), difference);

        fraction1 = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(bigArray(-1L, 1L), difference);

        fraction1 = new BigFraction(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.valueOf(3));
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(bigArray(1L, 6L), difference);
    }
    @Test
    void multiplyBigFraction() {
        BigFraction fraction1 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        BigFraction fraction2 = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
        BigFraction product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(bigArray(0L, 1L), product);

        fraction1 = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(bigArray(0L, 1L), product);

        fraction1 = new BigFraction(BigInteger.ONE, BigInteger.valueOf(3));
        fraction2 = new BigFraction(BigInteger.valueOf(3), BigInteger.ONE);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(bigArray(1L, 1L), product);

        fraction1 = new BigFraction(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.valueOf(3));
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(bigArray(1L, 6L), product);
    }
    @Test
    void divideBigFraction() {
        BigFraction dividend = new BigFraction(BigInteger.ONE, BigInteger.ONE);
        BigFraction divider = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
        assertThrows(IllegalArgumentException.class, () -> dividend.divide(divider));

        BigFraction fraction1 = new BigFraction(BigInteger.ONE, BigInteger.valueOf(3));
        BigFraction fraction2 = new BigFraction(BigInteger.valueOf(3), BigInteger.ONE);
        BigFraction quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(bigArray(1L, 9L), quotient);

        fraction1 = new BigFraction(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new BigFraction(BigInteger.ONE, BigInteger.valueOf(3));
        quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(bigArray(3L, 2L), quotient);
    }
}