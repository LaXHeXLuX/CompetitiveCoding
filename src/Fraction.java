import java.math.BigInteger;
import java.util.*;

public abstract class Fraction<T> {
    public T numerator;
    public T denominator;

    abstract Fraction<T> add(Fraction<T> fraction);
    abstract Fraction<T> subtract(Fraction<T> fraction);
    abstract Fraction<T> multiply(Fraction<T> fraction);
    abstract Fraction<T> divide(Fraction<T> fraction);
    static int[][] convertToFraction(long divisible, Set<Long> modCycle, int[] reciprocalCycle) {
        Iterator<Long> it = modCycle.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().equals(divisible)) break;
            i++;
        }

        return getInts(reciprocalCycle, i);
    }
    static int[][] convertToFraction(BigInteger divisible, Set<BigInteger> modCycle, int[] reciprocalCycle) {
        Iterator<BigInteger> it = modCycle.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().equals(divisible)) break;
            i++;
        }

        return getInts(reciprocalCycle, i);
    }
    private static int[][] getInts(int[] reciprocalCycle, int i) {
        int[] nonCycle = new int[i];
        int[] cycle = new int[reciprocalCycle.length-i];
        System.arraycopy(reciprocalCycle, 0, nonCycle, 0, nonCycle.length);
        System.arraycopy(reciprocalCycle, i, cycle, 0, cycle.length);
        return new int[][] {nonCycle, cycle};
    }
    abstract Fraction<T> simplify();
    abstract T[] asArray();
    public String toString() {
        return "{" + numerator + ", " + denominator + "}";
    }
}

class LongFraction extends Fraction<Long> {
    public LongFraction(long numerator, long denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Fraction: denominator can't be zero!");
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Long[] asArray() {
        return new Long[] {numerator, denominator};
    }

    @Override
    public LongFraction add(Fraction<Long> fraction) {
        long gcd = Divisors.greatestCommonDivisor(denominator, fraction.denominator);
        long newDenominator = denominator * fraction.denominator / gcd;
        long newNumerator1 = numerator * (newDenominator/denominator);
        long newNumerator2 = fraction.numerator * (newDenominator/ fraction.denominator);
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
        if (denominator.compareTo(1L) == 0) return new int[][] {};

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

class BigFraction extends Fraction<BigInteger> {

    public BigFraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.compareTo(BigInteger.ZERO) == 0) throw new IllegalArgumentException("Fraction: denominator can't be zero!");
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
        if (denominator.compareTo(BigInteger.ONE) == 0) return new int[][] {};

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
        return new BigInteger[] {numerator, denominator};
    }
}
