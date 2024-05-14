import java.math.BigInteger;

public abstract class Fraction<T> {
    public T numerator;
    public T denominator;

    abstract Fraction<T> add(Fraction<T> fraction);
    abstract Fraction<T> subtract(Fraction<T> fraction);
    abstract Fraction<T> multiply(Fraction<T> fraction);
    abstract Fraction<T> divide(Fraction<T> fraction);
    abstract Fraction<T> simplify();
    abstract T[] asArray();
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
