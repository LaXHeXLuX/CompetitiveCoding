import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

    @Test
    void sieveOfPrimes() {
        assertArrayEquals(new boolean[]{false, false, true, true, false, true, false, true, false, false}, Primes.sieveOfPrimes(10));
        boolean[] manyPrimes = Primes.sieveOfPrimes(100_000);
        assertEquals(100_000, manyPrimes.length);
        assertFalse(manyPrimes[100]);
        assertTrue(manyPrimes[101]);
        assertFalse(manyPrimes[1000]);
        assertFalse(manyPrimes[1001]);
        assertFalse(manyPrimes[1234]);
        assertFalse(manyPrimes[10000]);
        assertFalse(manyPrimes[10001]);
        assertTrue(manyPrimes[33331]);
        assertFalse(manyPrimes[34567]);
        assertFalse(manyPrimes[99999]);
    }

    @Test
    void findPrimeFactors() {
        assertArrayEquals(new long[] {}, Primes.findPrimeFactors(1));
        assertArrayEquals(new long[] {2}, Primes.findPrimeFactors(2));
        assertArrayEquals(new long[] {7}, Primes.findPrimeFactors(7));
        assertArrayEquals(new long[] {2, 2, 3}, Primes.findPrimeFactors(12));
        assertArrayEquals(new long[] {3, 23}, Primes.findPrimeFactors(69));
        assertArrayEquals(new long[] {2, 2, 2, 2, 2, 2, 2, 2}, Primes.findPrimeFactors(256));
        assertArrayEquals(new long[] {2, 2, 2, 2, 2, 5, 5, 5, 5, 5}, Primes.findPrimeFactors(100000));
    }

    @Test
    void isPrime() {
        assertFalse(Primes.isPrime(1));
        assertTrue(Primes.isPrime(2));

        assertTrue(Primes.isPrime(289586599663L));
        assertTrue(Primes.isPrime(217203134209L));
        assertFalse(Primes.isPrime(217203134207L));
    }

    @Test
    void nthPrime() {
        assertEquals(2, Primes.nthPrime(1));
        assertEquals(541, Primes.nthPrime(100));
        assertEquals(4127, Primes.nthPrime(567));
        assertEquals(104729, Primes.nthPrime(10_000));
        assertEquals(-1, Primes.nthPrime(-1));
    }

    @Test
    void areRelativePrimes() {
        for (int i = 2; i < 10; i++) {
            assertTrue(Primes.areRelativePrimes(1, i));
        }

        assertFalse(Primes.areRelativePrimes(6, 9));
        assertTrue(Primes.areRelativePrimes(2, 7));
        assertFalse(Primes.areRelativePrimes(21, 70));
        assertTrue(Primes.areRelativePrimes(81, 64));

        long[] primes1 = {2};
        long[] primes2 = {2, 3};
        long[] primes3 = {7, 11};

        assertFalse(Primes.areRelativePrimes(primes1, primes2));
        assertTrue(Primes.areRelativePrimes(primes1, primes3));
        assertTrue(Primes.areRelativePrimes(primes2, primes3));
    }

    @Test
    void eulersTotient() {
        assertEquals(0, Primes.eulersTotient(1));
        assertEquals(1, Primes.eulersTotient(2));
        assertEquals(2, Primes.eulersTotient(3));
        assertEquals(2, Primes.eulersTotient(3));

        assertEquals(6, Primes.eulersTotient(9));
        assertEquals(10, Primes.eulersTotient(11));

        assertEquals(79180, Primes.eulersTotient(87109));
    }

    @Test
    void upperBoundForNumberOfSmallerPrimes() {
        assertEquals(0, Primes.upperBoundForNumberOfSmallerPrimes(1));
        assertTrue(Primes.upperBoundForNumberOfSmallerPrimes(10) >= 4);
        assertTrue(Primes.upperBoundForNumberOfSmallerPrimes(100) >= 25);
        assertTrue(Primes.upperBoundForNumberOfSmallerPrimes(1_000_000) >= 78_498);
    }
}