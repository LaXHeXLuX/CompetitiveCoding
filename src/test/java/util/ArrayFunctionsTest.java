package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayFunctionsTest {

    @Test
    void contains() {
        Object[] arrObject = {'a', "B", 3, 4L};
        assertTrue(ArrayFunctions.contains('a', arrObject));
        assertFalse(ArrayFunctions.contains("C", arrObject));

        int[] arrInt = {512, 0, 1, 77, 5, 6, 333, 321};
        assertTrue(ArrayFunctions.contains(512, arrInt));
        assertTrue(ArrayFunctions.contains(0, arrInt));
        assertTrue(ArrayFunctions.contains(321, arrInt));
        assertFalse(ArrayFunctions.contains(-1, arrInt));
        assertFalse(ArrayFunctions.contains(7, arrInt));
        assertFalse(ArrayFunctions.contains(323, arrInt));

        long[] arrLong = {10, 10_000_000_000_000_000L, 100, 10_000, 100_000_000};
        assertTrue(ArrayFunctions.contains(10L, arrLong));
        assertTrue(ArrayFunctions.contains(10_000L, arrLong));
        assertTrue(ArrayFunctions.contains(10_000_000_000_000_000L, arrLong));
        assertFalse(ArrayFunctions.contains(1_000L, arrLong));
        assertFalse(ArrayFunctions.contains(1_000_000L, arrLong));
        assertFalse(ArrayFunctions.contains(1_000_000_000_000L, arrLong));

        String[] arrString = {"apple", "peach", "banana", "fun", "x-ray", "golf"};
        assertTrue(ArrayFunctions.contains("apple", arrString));
        assertTrue(ArrayFunctions.contains("fun", arrString));
        assertTrue(ArrayFunctions.contains("x-ray", arrString));
        assertFalse(ArrayFunctions.contains("delta", arrString));
        assertFalse(ArrayFunctions.contains("november", arrString));
        assertFalse(ArrayFunctions.contains("", arrString));
    }
    @Test
    void sortedContains() {
        assertFalse(ArrayFunctions.sortedContains(0, new int[] {}));
        assertTrue(ArrayFunctions.sortedContains(0, new int[] {0}));

        int[] arrInt = {0, 1, 5, 6, 77, 321};
        assertTrue(ArrayFunctions.sortedContains(77, arrInt));
        assertTrue(ArrayFunctions.sortedContains(0, arrInt));
        assertTrue(ArrayFunctions.sortedContains(321, arrInt));
        assertFalse(ArrayFunctions.sortedContains(-1, arrInt));
        assertFalse(ArrayFunctions.sortedContains(7, arrInt));
        assertFalse(ArrayFunctions.sortedContains(323, arrInt));

        String[] arrString = {"a", "b", "c", "x", "y", "z"};
        assertTrue(ArrayFunctions.sortedContains("a", arrString));
        assertTrue(ArrayFunctions.sortedContains("c", arrString));
        assertTrue(ArrayFunctions.sortedContains("y", arrString));
        assertFalse(ArrayFunctions.sortedContains("d", arrString));
        assertFalse(ArrayFunctions.sortedContains("m", arrString));
        assertFalse(ArrayFunctions.sortedContains("", arrString));
    }
    @Test
    void switchElement() {
        Object[] arrObject = {1, 'a', 3.5f, "good", 'a', 'b', 10, 1};
        assertArrayEquals(arrObject, ArrayFunctions.switchElement(arrObject, null, "not null"));
        assertArrayEquals(arrObject, ArrayFunctions.switchFirstElement(arrObject, null, "not null"));

        int[] arrInt = {2, 3, 2, 0, 7, 11, 6, 2, 7};
        assertArrayEquals(new int[] {5, 3, 5, 0, 7, 11, 6, 5, 7}, ArrayFunctions.switchElement(arrInt, 2, 5));
        assertArrayEquals(new int[] {5, 3, 2, 0, 7, 11, 6, 2, 7}, ArrayFunctions.switchFirstElement(arrInt, 2, 5));

        byte[] arrByte = {1, 3, 1, 0, 7, 11, 6, 1, 7};
        assertArrayEquals(new byte[] {5, 3, 5, 0, 7, 11, 6, 5, 7}, ArrayFunctions.switchElement(arrByte, (byte) 1, (byte) 5));
        assertArrayEquals(new byte[] {5, 3, 1, 0, 7, 11, 6, 1, 7}, ArrayFunctions.switchFirstElement(arrByte, (byte) 1, (byte) 5));

        short[] arrShort = {4, 2, 5, 1, 5, 3};
        assertArrayEquals(new short[] {4, 2, 7, 1, 7, 3}, ArrayFunctions.switchElement(arrShort, (short) 5, (short) 7));
        assertArrayEquals(new short[] {4, 2, 7, 1, 5, 3}, ArrayFunctions.switchFirstElement(arrShort, (short) 5, (short) 7));

        long[] arrLong = {1_000_000_000_000_000L, 20_000_000_000_000_000L, 30, 40, 1_000_000_000_000_000L};
        long[] arrLongExpected1 = {1, 20_000_000_000_000_000L, 30, 40, 1};
        long[] arrLongExpected2 = {1, 20_000_000_000_000_000L, 30, 40, 1_000_000_000_000_000L};
        assertArrayEquals(arrLongExpected1, ArrayFunctions.switchElement(arrLong, 1_000_000_000_000_000L, 1));
        assertArrayEquals(arrLongExpected2, ArrayFunctions.switchFirstElement(arrLong, 1_000_000_000_000_000L, 1));

        float[] arrFloat = {1.5f, 2.0f, 3.5f, 2.0f};
        assertArrayEquals(new float[] {1.5f, 0.0f, 3.5f, 0.0f}, ArrayFunctions.switchElement(arrFloat, 2.0f, 0.0f));
        assertArrayEquals(new float[] {1.5f, 0.0f, 3.5f, 2.0f}, ArrayFunctions.switchFirstElement(arrFloat, 2.0f, 0.0f));

        double[] arrDouble = {5.5, 10.0, 15.5, 10.0};
        assertArrayEquals(new double[] {5.5, 20.0, 15.5, 20.0}, ArrayFunctions.switchElement(arrDouble, 10.0, 20.0));
        assertArrayEquals(new double[] {5.5, 20.0, 15.5, 10.0}, ArrayFunctions.switchFirstElement(arrDouble, 10.0, 20.0));

        char[] arrChar = {'a', 'b', 'c', 'a'};
        assertArrayEquals(new char[] {'x', 'b', 'c', 'x'}, ArrayFunctions.switchElement(arrChar, 'a', 'x'));
        assertArrayEquals(new char[] {'x', 'b', 'c', 'a'}, ArrayFunctions.switchFirstElement(arrChar, 'a', 'x'));

        boolean[] arrBoolean = {true, false, true};
        assertArrayEquals(new boolean[] {false, false, false}, ArrayFunctions.switchElement(arrBoolean, true, false));
        assertArrayEquals(new boolean[] {false, false, true}, ArrayFunctions.switchFirstElement(arrBoolean, true, false));

        String[] arrString = {"hello", "world", "good", "world"};
        String[] arrStringExpected1 = {"hello", "dog", "good", "dog"};
        String[] arrStringExpected2 = {"hello", "dog", "good", "world"};
        assertArrayEquals(arrStringExpected1, ArrayFunctions.switchElement(arrString, "world", "dog"));
        assertArrayEquals(arrStringExpected2, ArrayFunctions.switchFirstElement(arrString, "world", "dog"));
    }
    @Test
    void mergeSort() {
        assertArrayEquals(new Object[] {}, ArrayFunctions.mergeSort(new Object[] {}));
        assertArrayEquals(new Object[] {1}, ArrayFunctions.mergeSort(new Object[] {1}));

        int[] arrInt = {3, 5, 2, 100, 77, 1, 1, 7, 8, 3};
        assertArrayEquals(new int[] {1, 1, 2, 3, 3, 5, 7, 8, 77, 100}, ArrayFunctions.mergeSort(arrInt));

        long[] arrLong = {3, 5, 2, 1_000_000_000_000_000L, 77, 1, 1, 7, 8, 3};
        assertArrayEquals(new long[] {1, 1, 2, 3, 3, 5, 7, 8, 77, 1_000_000_000_000_000L}, ArrayFunctions.mergeSort(arrLong));

        String[] arrString = {"this", "is", "absolutely", "my", "best", "array", "ever"};
        String[] arrStringExpected = {"absolutely", "array", "best", "ever", "is", "my", "this"};
        assertArrayEquals(arrStringExpected, ArrayFunctions.mergeSort(arrString));
    }
    @Test
    void reverseArray() {
        int[] arrInt = {1, 2, 3, 4};
        assertArrayEquals(new int[] {4, 3, 2, 1}, ArrayFunctions.reverseArray(arrInt));

        String[] arrString = {"apple", "banana", "corn"};
        assertArrayEquals(new String[] {"corn", "banana", "apple"}, ArrayFunctions.reverseArray(arrString));

        boolean[][][] deepArrBoolean = new boolean[][][] {{{true}, {true, false}}, {{false}}, {}};
        assertArrayEquals(new boolean[][][] {{}, {{false}}, {{true}, {true, false}}}, ArrayFunctions.reverseArray(deepArrBoolean));
    }
    @Test
    void removeIndex() {
        assertArrayEquals(new Object[] {}, ArrayFunctions.removeIndex(new Object[] {""}, 0));
        assertArrayEquals(new Object[] {'a'}, ArrayFunctions.removeIndex(new Object[] {'a', 'b'}, 1));
        assertArrayEquals(new Object[] {'b'}, ArrayFunctions.removeIndex(new Object[] {'a', 'b'}, 0));

        assertArrayEquals(new int[] {1, 4, 2, 3}, ArrayFunctions.removeIndex(new int[] {1, 5, 4, 2, 3}, 1));

        assertArrayEquals(new String[] {"yes", "no"}, ArrayFunctions.removeIndex(new String[] {"yes", "maybe", "no"}, 1));
    }
    @Test
    void subArray() {
        Object[] arrObject = {0, 'a', "hello", 1_000_000_000_000L, 2.5, true};
        assertArrayEquals(new Object[] {'a'}, ArrayFunctions.subArray(arrObject, 1, 1));
        assertArrayEquals(arrObject, ArrayFunctions.subArray(arrObject, 0, arrObject.length-1));

        assertArrayEquals(new int[] {3, 4, 5}, ArrayFunctions.subArray(new int[] {1, 2, 3, 4, 5}, 2, 4));

        boolean[] arrBoolean = {true, true, false};
        assertArrayEquals(new boolean[] {true, false}, ArrayFunctions.subArray(arrBoolean, 1, 2));

        assertThrows(RuntimeException.class, () -> ArrayFunctions.subArray(new String[0], -1, 0));
        assertThrows(RuntimeException.class, () -> ArrayFunctions.subArray(new String[0], 0, 1));
    }
    @Test
    void concatenate() {
        Object[] arrObject1 = {};
        Object[] arrObject2 = {1, '2', "3", 4.0, 5L};
        Object[] arrObject3 = {"", 1234567890};
        assertArrayEquals(arrObject1, ArrayFunctions.concatenate(arrObject1, arrObject1));
        assertArrayEquals(arrObject2, ArrayFunctions.concatenate(arrObject2, arrObject1));
        assertArrayEquals(arrObject3, ArrayFunctions.concatenate(arrObject1, arrObject3));
        assertArrayEquals(new Object[] {1, '2', "3", 4.0, 5L, "", 1234567890}, ArrayFunctions.concatenate(arrObject2, arrObject3));

        assertArrayEquals(new int[] {1, 3, 2, 4}, ArrayFunctions.concatenate(new int[] {1, 3}, new int[] {2, 4}));

        assertArrayEquals(new boolean[] {true, false}, ArrayFunctions.concatenate(new boolean[] {true}, new boolean[] {false}));
    }
    @Test
    void commonElements() {
        Object[] arrObject1 = {1, '2', "3", 4L, 10.0};
        Object[] arrObject2 = {2, "3", 4, 5f, 10.0, 3, 10};
        Object[] arrObject3 = {};
        assertArrayEquals(arrObject1, ArrayFunctions.commonElements(arrObject1, arrObject1));
        assertArrayEquals(new Object[] {"3", 10.0}, ArrayFunctions.commonElements(arrObject1, arrObject2));
        assertArrayEquals(new Object[] {}, ArrayFunctions.commonElements(arrObject1, arrObject3));
        assertArrayEquals(new Object[] {}, ArrayFunctions.commonElements(arrObject3, arrObject2));

        int[] arrInt1 = {1, 2, 3};
        int[] arrInt2 = {3, 4, 5};
        assertArrayEquals(new int[] {3}, ArrayFunctions.commonElements(arrInt1, arrInt2));

        String[] arrString1 = {"hello", "world", "I", "was", "here", "here", "here"};
        String[] arrString2 = {"hello", "hello", "hello", "I", "here"};
        assertArrayEquals(new String[] {"hello", "I", "here"}, ArrayFunctions.commonElements(arrString1, arrString2));
    }
}