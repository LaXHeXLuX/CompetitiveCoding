package util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Divisors {
    public static long[] divisors(long n) {
        if (n < 1) throw new RuntimeException("Argument must be positive!");
        if (n == 1) return new long[] {1};
        List<Long> divisors = new ArrayList<>();
        divisors.add(1L);
        List<Long> biggerDivisors = new ArrayList<>();

        for (long i = 2; i <= Math.pow(n, 0.5); i++) {
            if (n % i == 0) {
                divisors.add(i);
                long counterPart = n/i;
                if (counterPart != i) biggerDivisors.add(counterPart);
            }
        }

        for (int i = biggerDivisors.size()-1; i >= 0; i--) {
            divisors.add(biggerDivisors.get(i));
        }

        divisors.add(n);
        return Converter.listToArr(divisors);
    }
    public static long sumOfDivisors(long n) {
        long[] divisors = divisors(n);
        long sum = 0;

        for (int i = 0; i < divisors.length-1; i++) {
            sum += divisors[i];
        }

        return sum;
    }
    public static long greatestCommonDivisor(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    public static BigInteger greatestBigCommonDivisor(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger t = b;
            b = a.remainder(b);
            a = t;
        }
        return a;
    }
}
