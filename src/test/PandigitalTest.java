import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandigitalTest {

    @Test
    void isPandigital() {
        for (int i = 1; i < 10; i++) {
            assertTrue(Pandigital.isPandigital(i, i));
        }
        assertFalse(Pandigital.isPandigital(12321));
        assertFalse(Pandigital.isPandigital(95768));
        assertTrue(Pandigital.isPandigital(95768, 5));

        int[] digits = {5, 9, 6, 7, 4, 8};
        assertFalse(Pandigital.isPandigital(digits));
        assertTrue(Pandigital.isPandigital(digits, 4));
    }

    @Test
    void groupIsPandigital() {
        assertTrue(Pandigital.groupIsPandigital(new int[] {2, 43, 816, 597}));
        assertTrue(Pandigital.groupIsPandigital(new int[] {918273645}));
        assertTrue(Pandigital.groupIsPandigital(new int[] {9, 1, 8, 2, 7, 3, 6, 4, 5}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 78, 91}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 78}));
    }
}