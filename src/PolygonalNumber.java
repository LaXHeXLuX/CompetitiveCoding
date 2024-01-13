import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PolygonalNumber {
    public static long polygonalNumberLong(long sides, long n) {
        return ((sides - 2)*n*n - (sides - 4)*n)/2;
    }
    public static BigInteger polygonalNumberBigInteger(long sides, long n) {
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigSides = BigInteger.valueOf(sides);
        BigInteger firstPart = bigSides.subtract(BigInteger.valueOf(2)).multiply(bigN).multiply(bigN);
        BigInteger secondPart = bigSides.subtract(BigInteger.valueOf(4)).multiply(bigN);

        return firstPart.subtract(secondPart).divide(BigInteger.TWO);
    }
    public static boolean isPolygonalNumber(long sides, long x) {
        if (sides == x) return true;
        long a = sides-2;
        long b = 4-sides;
        long c = -2*x;
        double n = (-b + Math.sqrt(b*b - 4*a*c))/(2*a);
        return n == Math.floor(n);
    }
    public static int[] isPolygonalNumber(long x) {
        List<Integer> possibleSides = new ArrayList<>();

        for (int sides = 3; sides < x; sides++) {
            if (sides < 0) break;
            if (isPolygonalNumber(sides, x)) possibleSides.add(sides);
        }
        if (possibleSides.size() == 0) return new int[0];
        return Converter.listToArr(possibleSides);
    }
}
