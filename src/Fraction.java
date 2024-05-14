public class Fraction {
    public long numerator;
    public long denominator;

    public Fraction(long numerator, long denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Fraction: denominator can't be zero!");
        if (denominator < 0) {
            denominator *= -1;
            numerator *= -1;
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
}
