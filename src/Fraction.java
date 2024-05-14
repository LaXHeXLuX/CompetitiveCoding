public class Fraction {
    public long numerator;
    public long denominator;

    public Fraction(long numerator, long denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Fraction: denominator can't be zero!");
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long[] asArray() {
        return new long[] {numerator, denominator};
    }

    public Fraction simplify() {
        long gcd = Divisors.greatestCommonDivisor(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    public Fraction add(Fraction fraction) {
        long gcd = Divisors.greatestCommonDivisor(denominator, fraction.denominator);
        long newDenominator = denominator * fraction.denominator / gcd;
        long newNumerator1 = numerator * (newDenominator/denominator);
        long newNumerator2 = fraction.numerator * (newDenominator/fraction.denominator);
        return new Fraction(newNumerator1 + newNumerator2, newDenominator);
    }

    public Fraction subtract(Fraction fraction) {
        return this.add(new Fraction(-fraction.numerator, fraction.denominator));
    }

    public Fraction multiply(Fraction fraction) {
        return new Fraction(numerator * fraction.numerator, denominator * fraction.denominator);
    }

    public Fraction divide(Fraction fraction) {
        return this.multiply(new Fraction(fraction.denominator, fraction.numerator));
    }
}
