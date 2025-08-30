package util;

import java.math.BigInteger;

public class Generifier {
    private static <T> void checkClass(T el1, T el2) {
        if (!el1.getClass().equals(el2.getClass())) {
            throw new IllegalArgumentException("Parameters are of different types!");
        }
    }
    public static <T> int compareTo(T el1, T el2) {
        checkClass(el1, el2);
        switch (el1.getClass().getName()) {
            case "java.lang.Integer" -> {
                return Integer.compare((int) el1, (int) el2);
            }
            case "java.lang.Double" -> {
                return Double.compare((double) el1, (double) el2);
            }
            case "java.lang.Boolean" -> {
                return Boolean.compare((boolean) el1, (boolean) el2);
            }
            case "java.lang.Character" -> {
                return Character.compare((char) el1, (char) el2);
            }
            case "java.lang.Byte" -> {
                return Byte.compare((byte) el1, (byte) el2);
            }
            case "java.lang.Short" -> {
                return Short.compare((short) el1, (short) el2);
            }
            case "java.lang.Long" -> {
                return Long.compare((long) el1, (long) el2);
            }
            case "java.lang.Float" -> {
                return Float.compare((float) el1, (float) el2);
            }
            case "java.lang.String" -> {
                return ((String) el1).compareTo((String) el2);
            }
            case "java.math.BigInteger" -> {
                return ((BigInteger) el1).compareTo((BigInteger) el2);
            }
            default -> throw new IllegalArgumentException("Unsupported type: " + el1.getClass());
        }
    }
}
