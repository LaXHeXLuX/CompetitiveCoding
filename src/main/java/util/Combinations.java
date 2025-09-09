package util;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class Combinations {

    public static BigInteger factorialBigInteger(int n) {
        if (n < 0) throw new RuntimeException("Argument can't be negative!");
        if (n < 2) return BigInteger.ONE;
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
    public static long factorial(int n) {
        if (n < 0) throw new RuntimeException("Argument can't be negative!");
        if (n < 2) return 1L;
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
            if (factorial < 0) throw new RuntimeException("Arguments too large!");
        }
        return factorial;
    }
    public static BigInteger nChooseMBigInteger(int n, int m) {
        BigInteger factorial1 = factorialBigInteger(n);
        BigInteger factorial2 = factorialBigInteger(m);
        BigInteger factorial3 = factorialBigInteger(n-m);
        return factorial1.divide(factorial2.multiply(factorial3));
    }
    public static long nChooseMOrderMattersLong(int n, int m) {
        long factorial1 = factorial(n);
        long factorial2 = factorial(n-m);
        return factorial1 / factorial2;
    }
    public static int[][] chooseNElements(int[] arr, int n, boolean orderMatters) {
        if (n == 0) return new int[][] {};
        if (n == 1) {
            int[][] output = new int[arr.length][];
            for (int i = 0; i < arr.length; i++) {
                output[i] = new int[] {arr[i]};
            }
            return output;
        }
        List<int[]> chosen = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int[] chooseNextFrom;
            if (orderMatters) chooseNextFrom = ArrayFunctions.removeIndex(arr, i);
            else chooseNextFrom = ArrayFunctions.subArray(arr, i, arr.length-1);
            int[][] previousOutput = chooseNElements(chooseNextFrom, n-1, orderMatters);

            for (int[] ints : previousOutput) {
                chosen.add(ArrayFunctions.concatenate(new int[] {arr[i]}, ints));
            }
        }

        return Converter.listToArr(chosen);
    }
    public static <T> T[] permutations(T arr) {
        return Converter.deepToPrimitiveArray(permutations(Converter.toWrapperArray(arr)));
    }
    @SuppressWarnings("unchecked")
    public static <T> T[][] permutations(T[] arr) {
        Class<?> type = arr.getClass().getComponentType();
        T[][] output = (T[][]) Array.newInstance(type, 1, arr.length);
        if (arr.length == 0) return output;
        if (arr.length == 1) {
            output[0][0] = arr[0];
            return output;
        }

        List<T[]> outputList = new ArrayList<>();
        T[] sorted = ArrayFunctions.mergeSort(arr);
        for (int i = 0; i < sorted.length; i++) {
            T el = sorted[i];
            if (i > 0 && el.equals(sorted[i-1])) continue;
            T[] others = (T[]) Array.newInstance(type, sorted.length-1);
            System.arraycopy(sorted, 0, others, 0, i);
            System.arraycopy(sorted, i+1, others, i, sorted.length-i-1);
            T[][] othersPermutations = permutations(others);
            for (T[] op : othersPermutations) {
                T[] perm = (T[]) Array.newInstance(type, sorted.length);
                perm[0] = el;
                System.arraycopy(op, 0, perm, 1, op.length);
                outputList.add(perm);
            }
        }
        output = (T[][]) Array.newInstance(type, outputList.size(), outputList.getFirst().length);
        return outputList.toArray(output);
    }
    public static boolean isPermutation(long n1, long n2) {
        return isPermutation(Converter.digitArray(n1), Converter.digitArray(n2));
    }
    public static <T> boolean isPermutation(T arr1, T arr2) {
        return isPermutation(Converter.toWrapperArray(arr1), Converter.toWrapperArray(arr2));
    }
    public static <T> boolean isPermutation(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) return false;
        Map<T, Integer> countMap = new HashMap<>();
        for (T el : arr1) {
            countMap.put(el, countMap.getOrDefault(el, 0) + 1);
        }
        for (T el : arr2) {
            if (!countMap.containsKey(el)) return false;
            countMap.put(el, countMap.get(el) - 1);
            if (countMap.get(el) == 0) countMap.remove(el);
        }

        return countMap.isEmpty();
    }
    public static <T> T nthPermutation(T arr, long n) {
        return Converter.toPrimitiveArray(nthPermutation(Converter.toWrapperArray(arr), n));
    }
    @SuppressWarnings("unchecked")
    public static <T> T[] nthPermutation(T[] arr, long n) {
        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        System.arraycopy(arr, 0, newArr, 0, newArr.length);
        if (n == 0 || arr.length < 2) return newArr;
        int[] plan = permutationPlan(n);
        for (int i = 0; i < plan.length; i++) {
            if (plan[i] == 0) continue;
            T el = newArr[i + plan[i]];
            T[] result = ArrayFunctions.subArray(newArr, i, newArr.length-1);
            result = ArrayFunctions.removeIndex(result, plan[i]);
            newArr[i] = el;
            System.arraycopy(result, 0, newArr, i+1, result.length);
        }
        return newArr;
    }
    private static int[] permutationPlan(long n) {
        long prod = 1;
        int i;
        for (i = 2; i < n; i++) {
            prod *= i;
            if (prod > n) break;
        }
        int[] plan = new int[i-1];
        for (i = 0; i < plan.length; i++) {
            long f = factorial(plan.length - i);
            plan[i] = Math.toIntExact(n / f);
            n = n % f;
            if (n == 0) break;
        }
        return plan;
    }
    public static int[][] combinationsOfGrowingNumbers(int start, int end, int amount) {
        if (amount == 1) {
            int[][] combinations = new int[end-start+1][];

            for (int i = 0; i <= end-start; i++) {
                combinations[i] = new int[] {start+i};
            }

            return combinations;
        }

        List<int[]> combinations = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            int[][] nextCombinations = combinationsOfGrowingNumbers(i, end, amount-1);

            for (int[] nextCombination : nextCombinations) {
                combinations.add(ArrayFunctions.concatenate(new int[] {i}, nextCombination));
            }
        }
        return Converter.listToArr(combinations);
    }
}
