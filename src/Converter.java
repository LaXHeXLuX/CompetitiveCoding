import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static <T> T listToArr(List<?> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("listToArr: list cannot be empty!");
        }
        return listToArr(list, list.getFirst().getClass());
    }
    @SuppressWarnings("unchecked")
    public static <T> T listToArr(List<?> list, Class<?> type) {
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(type, list.size());
        return toPrimitiveArray(list.toArray(arr));
    }
    public static <T> T deepListToArr(List<?> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("deepListToArr: list cannot be empty!");
        }
        if (!(list.getFirst() instanceof List)) {
            return toPrimitiveArray(listToArr(list));
        }
        Object[] arr = new Object[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = deepListToArr((List<?>) list.get(i));
        }
        return deepToPrimitiveArray(arr);
    }
    public static int[] arrStringToArrInt(String[] arr) {
        int[] arrInt = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrInt[i] = Integer.parseInt(arr[i]);
        }
        return arrInt;
    }
    public static int[] digitArray(long n) {
        if (n == 0) return new int[] {0};
        List<Integer> digitArray = new ArrayList<>();
        while (n > 0) {
            digitArray.add((int) (n % 10));
            n /= 10;
        }

        return ArrayFunctions.reverseArray((int[]) listToArr(digitArray));
    }
    public static int[] digitArray(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) return new int[] {0};
        List<Integer> digitArray = new ArrayList<>();
        while (n.compareTo(BigInteger.ZERO) != 0) {
            digitArray.add(n.remainder(BigInteger.TEN).intValue());
            n = n.divide(BigInteger.TEN);
        }
        return ArrayFunctions.reverseArray((int[]) listToArr(digitArray));
    }
    public static long digitFromArrayLong(int[] digitArray) {
        long n = 0;
        for (int digit : digitArray) n = 10*n + digit;
        return n;
    }
    public static int[] convertToBase(long n, int base) {
        if (n == 0) return new int[] {0};

        int[] convertedDigits = new int[(int)(Math.log(n)/Math.log(base))+1];
        for (int i = 0; i < convertedDigits.length; i++) {
            convertedDigits[convertedDigits.length-i-1] = (int) (n%base);
            n /= base;
        }

        return convertedDigits;
    }
    public static long convertFromBase(int[] digits, int base) {
        long convertedNumber = 0;
        for (int i = 0; i < digits.length; i++) {
            convertedNumber += (long) (digits[digits.length-i-1] * Math.pow(base, i));
        }

        return convertedNumber;
    }
    public static int[] booleanArrToIntArr(boolean[] arr) {
        int counter = 0;
        for (boolean value : arr) if (value) counter++;

        int[] newArr = new int[counter];

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                newArr[index] = i;
                index++;
            }
        }

        return newArr;
    }
    public static boolean[] booleanConversion(int[] arr) {
        boolean[] output = new boolean[arr.length];

        for (int i = 0; i < output.length; i++) {
            output[i] = arr[i] != 0;
        }

        return output;
    }
    public static int[] booleanConversion(boolean[] arr) {
        int[] output = new int[arr.length];

        for (int i = 0; i < output.length; i++) {
            output[i] = arr[i] ? 1 : 0;
        }

        return output;
    }
    @SuppressWarnings("unchecked")
    public static <T> T[] toWrapperArray(Object array) {
        int length = Array.getLength(array);
        Class<?> wrapperType = getWrapperType(array.getClass().getComponentType());
        Object[] wrapperArray = (Object[]) Array.newInstance(wrapperType, length);

        for (int i = 0; i < length; i++) {
            Array.set(wrapperArray, i, Array.get(array, i));
        }

        return (T[]) wrapperArray;
    }
    public static Class<?> getWrapperType(Class<?> componentType) {
        return switch (componentType.getName()) {
            case "int" -> Integer.class;
            case "byte" -> Byte.class;
            case "short" -> Short.class;
            case "long" -> Long.class;
            case "float" -> Float.class;
            case "double" -> Double.class;
            case "char" -> Character.class;
            case "boolean" -> Boolean.class;
            default -> componentType;
        };
    }
    @SuppressWarnings("unchecked")
    public static <T> T toPrimitiveArray(Object array) {
        Object[] wrapperArray;
        try {
            wrapperArray = (Object[]) array;
        } catch (ClassCastException e) {
            return (T) array;
        }
        int length = wrapperArray.length;

        Class<?> componentType = getPrimitiveType(wrapperArray.getClass().getComponentType());
        Object primitiveArray = Array.newInstance(componentType, length);

        for (int i = 0; i < length; i++) {
            Array.set(primitiveArray, i, wrapperArray[i]);
        }

        return (T) primitiveArray;
    }
    public static Class<?> getPrimitiveType(Class<?> wrapperType) {
        int dimensions = 0;
        Class<?> type = wrapperType;
        while (type.isArray()) {
            dimensions++;
            type = type.getComponentType();
        }

        switch (type.getName()) {
            case "java.lang.Integer" -> { return getPrimitiveArrayClass(int.class, dimensions); }
            case "java.lang.Double" -> { return getPrimitiveArrayClass(double.class, dimensions); }
            case "java.lang.Boolean" -> { return getPrimitiveArrayClass(boolean.class, dimensions); }
            case "java.lang.Character" -> { return getPrimitiveArrayClass(char.class, dimensions); }
            case "java.lang.Byte" -> { return getPrimitiveArrayClass(byte.class, dimensions); }
            case "java.lang.Short" -> { return getPrimitiveArrayClass(short.class, dimensions); }
            case "java.lang.Long" -> { return getPrimitiveArrayClass(long.class, dimensions); }
            case "java.lang.Float" -> { return getPrimitiveArrayClass(float.class, dimensions); }
            default -> { return getPrimitiveArrayClass(type, dimensions); }
        }
    }
    private static Class<?> getPrimitiveArrayClass(Class<?> componentType, int dimensions) {
        if (dimensions <= 0) {
            return componentType;
        }
        int[] dummyArray = new int[dimensions];
        Object array = Array.newInstance(componentType, dummyArray);
        return array.getClass();
    }
    @SuppressWarnings("unchecked")
    public static <T> T deepToPrimitiveArray(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("deepToPrimitiveArray: not an arrary!");
        }

        int length = Array.getLength(array);
        Object primitiveArray = Array.newInstance(getPrimitiveType(array.getClass().getComponentType()), length);

        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            if (element.getClass().isArray()) {
                element = deepToPrimitiveArray(element);
            }
            Array.set(primitiveArray, i, element);
        }

        return (T) primitiveArray;
    }
    @SuppressWarnings("unchecked")
    public static <T> T convertNumberTo(Class<T> targetType, Number value) {
        return switch (targetType.getName()) {
            case "int", "java.lang.Integer" -> (T) Integer.valueOf(value.intValue());
            case "long", "java.lang.Long" -> (T) Long.valueOf(value.longValue());
            case "double", "java.lang.Double" -> (T) Double.valueOf(value.doubleValue());
            default -> throw new IllegalArgumentException("Unsupported type");
        };
    }
}
