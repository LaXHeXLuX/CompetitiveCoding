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
    void isPrimeBigInteger() {
        assertTrue(Primes.isPrime(782948398248371L));
        assertTrue(Primes.isPrime(647340319259941L));
        assertFalse(Primes.isPrime(100000000000000000L));
        assertFalse(Primes.isPrime(999999999999999999L));
        assertFalse(Primes.isPrime(395196502485130712L));
    }

    @Test
    void nthPrime() {
        assertEquals(2, Primes.nthPrime(1));
        assertEquals(541, Primes.nthPrime(100));
        assertEquals(4127, Primes.nthPrime(567));
        assertEquals(104729, Primes.nthPrime(10_000));
        assertEquals(-1, Primes.nthPrime(-1));
    }
}