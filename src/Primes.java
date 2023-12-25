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
            if (compositeNumber < 0) return;
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
        return Converter.listToArrLong(primeFactors);
    }
    public static boolean isPrime(long n) {
        if (n % 2 == 0) return false;

        for (int i = 3; i < Math.sqrt(n); i+=2) {
            if (n % i == 0) return false;
        }

        return true;
    }
    public static int nthPrime(int n) {
        int upperBound = upperBoundForNthPrime(n);
        boolean[] primes = sieveOfPrimes(upperBound);
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
}
