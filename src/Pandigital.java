public class Pandigital {
    public static boolean isPandigital(long n) {
        return isPandigital(n, 1);
    }
    public static boolean isPandigital(long n, int startingDigit) {
        int[] digits = Converter.digitArray(n);

        return isPandigital(digits, startingDigit);
    }
    public static boolean isPandigital(int[] digits) {
        return isPandigital(digits, 1);
    }
    public static boolean isPandigital(int[] digits, int startingDigit) {
        digits = ArrayFunctions.mergeSort(digits);

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != i+startingDigit) return false;
        }
        return true;
    }
    public static boolean groupIsPandigital(int[] numbers) {
        int[][] groupDigits = new int[numbers.length][];
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            groupDigits[i] = Converter.digitArray(numbers[i]);
            sum += groupDigits[i].length;
        }

        return groupIsPandigitalHelper(groupDigits, sum);
    }
    private static boolean groupIsPandigitalHelper(int[][] groupDigits, int sum) {
        if (sum != 9) return false;
        int[] digits = new int[sum];
        int index = 0;
        for (int[] groupDigit : groupDigits) {
            for (int i : groupDigit) {
                digits[index] = i;
                index++;
            }
        }

        digits = ArrayFunctions.mergeSort(digits);

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != i+1) return false;
        }
        return true;
    }
}
