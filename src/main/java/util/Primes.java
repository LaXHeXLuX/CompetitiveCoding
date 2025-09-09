package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {
    public static boolean[] sieveOfPrimes(int limit) {
        boolean[] primes = new boolean[limit];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        primes[2] = true;
        removeProductsOfN(primes, 2);
        for (int i = 3; i < Math.pow(primes.length, 0.5)+1; i+=2) {
            if (!primes[i]) continue;
            removeProductsOfN(primes, i);
        }
        return primes;
    }
    public static void removeProductsOfN(boolean[] primes, int n) {
        int compositeNumber = n*n;
        while (compositeNumber < primes.length) {
            primes[compositeNumber] = false;
            compositeNumber += n;
        }
    }
    public static long[] findPrimeFactors(long n) {
        List<Long> primeFactors = new ArrayList<>();
        while (n > 1) {
            boolean nIsPrime = true;
            for (long i = 2; i < Math.pow(n, 0.5)+1; i++) {
                if (n % i == 0) {
                    n /= i;
                    primeFactors.add(i);
                    nIsPrime = false;
                    break;
                }
            }
            if (nIsPrime) {
                primeFactors.add(n);
                break;
            }
        }
        if (primeFactors.isEmpty()) return new long[0];
        return Converter.listToArr(primeFactors);
    }
    public static boolean isPrime(long n) {
        if (n < 0) throw new RuntimeException("n can't be negative!");
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            if (n % i == 0) return false;
        }

        return true;
    }
    public static int nthPrime(int n) {
        int upperBound = upperBoundForNthPrime(n);
        boolean[] primes = sieveOfPrimes(upperBound+1);
        int counter = 0;

        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) counter++;
            if (counter == n) return i;
        }

        return -1;
    }
    private static int upperBoundForNthPrime(int n) {
        if (n < 6) return 12;
        double logN = Math.log(n);
        return (int) (n*logN + n*Math.log(logN));
    }
    public static int upperBoundForNumberOfSmallerPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        return (int) ((n / Math.log(n)) * (1 + 1.2762 / Math.log(n)));
    }
    public static boolean areRelativePrimes(long n1, long n2) {
        return Divisors.greatestCommonDivisor(n1, n2) == 1;
    }
    public static boolean areRelativePrimes(long[] primes1, long[] primes2) {
        return ArrayFunctions.commonElements(primes1, primes2).length == 0;
    }
    public static long eulersTotient(long n) {
        if (n == 1) return 0;
        long[] primesFactors = findPrimeFactors(n);
        return eulersTotient(primesFactors);
    }
    public static long eulersTotient(long[] primeFactors) {
        long totient = (primeFactors[0]-1);
        for (int i = 1; i < primeFactors.length; i++) {
            if (primeFactors[i] == primeFactors[i-1]) totient *= primeFactors[i];
            else totient *= primeFactors[i]-1;
        }
        return totient;
    }
}
