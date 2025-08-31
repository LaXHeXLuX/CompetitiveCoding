package util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LongFraction extends Fraction<Long> {
    public LongFraction(long numerator, long denominator) {
        if (denominator == 0) throw new IllegalArgumentException("util.Fraction: denominator can't be zero!");
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Long[] asArray() {
        return new Long[]{numerator, denominator};
    }

    @Override
    public LongFraction add(Fraction<Long> fraction) {
        long gcd = Divisors.greatestCommonDivisor(denominator, fraction.denominator);
        long newDenominator = denominator * fraction.denominator / gcd;
        long newNumerator1 = numerator * (newDenominator / denominator);
        long newNumerator2 = fraction.numerator * (newDenominator / fraction.denominator);
        return new LongFraction(newNumerator1 + newNumerator2, newDenominator);
    }

    public LongFraction simplify() {
        long gcd = Divisors.greatestCommonDivisor(numerator, denominator);
        return new LongFraction(numerator / gcd, denominator / gcd);
    }

    public LongFraction subtract(Fraction<Long> fraction) {
        return this.add(new LongFraction(-fraction.numerator, fraction.denominator));
    }

    public LongFraction multiply(Fraction<Long> fraction) {
        return new LongFraction(numerator * fraction.numerator, denominator * fraction.denominator);
    }

    public LongFraction divide(Fraction<Long> fraction) {
        return this.multiply(new LongFraction(fraction.denominator, fraction.numerator));
    }

    public int[][] getCycle() {
        if (numerator.compareTo(0L) <= 0) throw new RuntimeException("Numerator has to be more than 0");
        if (denominator.compareTo(1L) == 0) return new int[][]{};

        LongFraction simple = this.simplify();
        List<Integer> reciprocalCycle = new ArrayList<>();
        Set<Long> modCycle = new LinkedHashSet<>();
        Long divisible = simple.numerator % simple.denominator;

        while (divisible.compareTo(0L) != 0) {
            if (!modCycle.add(divisible)) {
                return convertToFraction(divisible, modCycle, Converter.listToArr(reciprocalCycle));
            }
            divisible *= 10;
            while (divisible < simple.denominator) {
                reciprocalCycle.add(0);
                if (!modCycle.add(divisible)) {
                    return convertToFraction(divisible, modCycle, Converter.listToArr(reciprocalCycle));
                }
                divisible *= 10;
            }
            reciprocalCycle.add((int) (divisible / simple.denominator));
            divisible = divisible % simple.denominator;
        }

        return convertToFraction(divisible, modCycle, Converter.listToArr(reciprocalCycle));
    }
}
