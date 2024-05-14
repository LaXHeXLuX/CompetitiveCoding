import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    void assertFractionEquals(long[] fractionAsArray, Fraction fraction) {
        assertArrayEquals(fractionAsArray, fraction.asArray());
    }

    void assertSimplifiedFractionEquals(long[] fractionAsArray, Fraction fraction) {
        assertArrayEquals(fractionAsArray, fraction.simplify().asArray());
    }

    @Test
    void createFraction() {
        assertThrows(IllegalArgumentException.class, () -> new Fraction(0, 0));
        assertFractionEquals(new long[] {0, 1}, new Fraction(0, 1));
        assertFractionEquals(new long[] {-1, 1}, new Fraction(1, -1));
    }

    @Test
    void simplifyFraction() {
        Fraction fraction = new Fraction(1, 1);
        assertFractionEquals(new long[] {1, 1}, fraction.simplify());

        fraction = new Fraction(2, 2);
        assertFractionEquals(new long[] {1, 1}, fraction.simplify());

        fraction = new Fraction(26, 8);
        assertFractionEquals(new long[] {13, 4}, fraction.simplify());
    }

    @Test
    void addFraction() {
        Fraction fraction1 = new Fraction(1, 1);
        Fraction fraction2 = new Fraction(0, 1);
        Fraction sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(new long[] {1, 1}, sum);

        fraction1 = new Fraction(1, 1);
        fraction2 = new Fraction(1, 1);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(new long[] {2, 1}, sum);

        fraction1 = new Fraction(1, 2);
        fraction2 = new Fraction(1, 3);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(new long[] {5, 6}, sum);
    }

    @Test
    void subtractFraction() {
        Fraction fraction1 = new Fraction(1, 1);
        Fraction fraction2 = new Fraction(0, 1);
        Fraction difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(new long[] {1, 1}, difference);

        fraction1 = new Fraction(0, 1);
        fraction2 = new Fraction(1, 1);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(new long[] {-1, 1}, difference);

        fraction1 = new Fraction(1, 2);
        fraction2 = new Fraction(1, 3);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(new long[] {1, 6}, difference);
    }

    @Test
    void multiplyFraction() {
        Fraction fraction1 = new Fraction(1, 1);
        Fraction fraction2 = new Fraction(0, 1);
        Fraction product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new long[] {0, 1}, product);

        fraction1 = new Fraction(0, 1);
        fraction2 = new Fraction(1, 1);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new long[] {0, 1}, product);

        fraction1 = new Fraction(1, 3);
        fraction2 = new Fraction(3, 1);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new long[] {1, 1}, product);

        fraction1 = new Fraction(1, 2);
        fraction2 = new Fraction(1, 3);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(new long[] {1, 6}, product);
    }

    @Test
    void divideFraction() {
        Fraction dividend = new Fraction(1, 1);
        Fraction divider = new Fraction(0, 1);
        assertThrows(IllegalArgumentException.class, () -> dividend.divide(divider));

        Fraction fraction1 = new Fraction(1, 3);
        Fraction fraction2 = new Fraction(3, 1);
        Fraction quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(new long[] {1, 9}, quotient);

        fraction1 = new Fraction(1, 2);
        fraction2 = new Fraction(1, 3);
        quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(new long[] {3, 2}, quotient);
    }
}