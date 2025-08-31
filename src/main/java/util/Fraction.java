package util;

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

