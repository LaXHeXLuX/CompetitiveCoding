import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    void assertFractionEquals(long[] fractionAsArray, Fraction fraction) {
        assertArrayEquals(fractionAsArray, fraction.asArray());
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
    }
}