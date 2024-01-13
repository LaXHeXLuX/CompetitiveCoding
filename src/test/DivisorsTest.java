import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisorsTest {

    @Test
    void divisors() {
        assertArrayEquals(new long[] {1}, Divisors.divisors(1));
        assertArrayEquals(new long[] {1, 2, 5, 10}, Divisors.divisors(10));
        assertArrayEquals(new long[] {1, 2, 4, 8, 16, 32}, Divisors.divisors(32));
        assertArrayEquals(new long[] {1, 101}, Divisors.divisors(101));
        assertArrayEquals(new long[] {1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000}, Divisors.divisors(1000));
    }

    @Test
    void sumOfDivisors() {
        assertEquals(1, Divisors.sumOfDivisors(2));
        assertEquals(28, Divisors.sumOfDivisors(28));
        assertEquals(1, Divisors.sumOfDivisors(101));
        assertEquals(810, Divisors.sumOfDivisors(360));
    }

    @Test
    void greatestCommonDivisor() {
        for (int i = 1; i < 10; i++) {
            assertEquals(i, Divisors.greatestCommonDivisor(i, i));
        }
        assertEquals(1, Divisors.greatestCommonDivisor(3, 5));
        assertEquals(10, Divisors.greatestCommonDivisor(100, 10));
        assertEquals(4, Divisors.greatestCommonDivisor(28, 60));
    }
}