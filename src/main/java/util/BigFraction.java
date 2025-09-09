package util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BigFraction extends Fraction<BigInteger> {

    public BigFraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.compareTo(BigInteger.ZERO) == 0)
            throw new IllegalArgumentException("util.Fraction: denominator can't be zero!");
        if (denominator.compareTo(BigInteger.ZERO) < 0) {
            denominator = denominator.multiply(BigInteger.valueOf(-1));
            numerator = numerator.multiply(BigInteger.valueOf(-1));
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public BigFraction add(Fraction<BigInteger> fraction) {
        BigInteger gcd = Divisors.greatestBigCommonDivisor(denominator, fraction.denominator);
        BigInteger newDenominator = denominator.multiply(fraction.denominator).divide(gcd);
        BigInteger newNumerator1 = numerator.multiply(newDenominator).divide(denominator);
        BigInteger newNumerator2 = fraction.numerator.multiply(newDenominator).divide(fraction.denominator);
        return new BigFraction(newNumerator1.add(newNumerator2), newDenominator);
    }

    @Override
    public BigFraction subtract(Fraction<BigInteger> fraction) {
        BigInteger negative = fraction.numerator.multiply(BigInteger.valueOf(-1));
        return this.add(new BigFraction(negative, fraction.denominator));
    }

    @Override
    public BigFraction multiply(Fraction<BigInteger> fraction) {
        return new BigFraction(numerator.multiply(fraction.numerator), denominator.multiply(fraction.denominator));
    }

    @Override
    public BigFraction divide(Fraction<BigInteger> fraction) {
        return this.multiply(new BigFraction(fraction.denominator, fraction.numerator));
    }

    public int[][] getCycle() {
        if (numerator.compareTo(BigInteger.ZERO) <= 0) throw new RuntimeException("Numerator has to be more than 0");
        if (denominator.compareTo(BigInteger.ONE) == 0) return new int[][]{};

        BigFraction simple = this.simplify();
        List<Integer> reciprocalCycle = new ArrayList<>();
        Set<BigInteger> modCycle = new LinkedHashSet<>();
        BigInteger divisible = simple.numerator.remainder(simple.denominator);

        while (divisible.compareTo(BigInteger.ZERO) != 0) {
            if (!modCycle.add(divisible)) {
                return convertToFraction(divisible, modCycle, Converter.listToArr(reciprocalCycle));
            }
            divisible = divisible.multiply(BigInteger.TEN);
            while (divisible.compareTo(simple.denominator) < 0) {
                reciprocalCycle.add(0);
                if (!modCycle.add(divisible)) {
                    return convertToFraction(divisible, modCycle, Converter.listToArr(reciprocalCycle));
                }
                divisible = divisible.multiply(BigInteger.TEN);
            }
            reciprocalCycle.add(divisible.divide(simple.denominator).intValue());
            divisible = divisible.remainder(simple.denominator);
        }

        return convertToFraction(divisible, modCycle, Converter.listToArr(reciprocalCycle));
    }

    @Override
    public BigFraction simplify() {
        BigInteger gcd = Divisors.greatestBigCommonDivisor(numerator, denominator);
        return new BigFraction(numerator.divide(gcd), denominator.divide(gcd));
    }

    @Override
    public BigInteger[] asArray() {
        return new BigInteger[]{numerator, denominator};
    }

    @Override
    public int compareTo(Fraction<BigInteger> fraction) {
        return this.numerator.multiply(fraction.denominator).compareTo(fraction.numerator.multiply(this.denominator));
    }
}
