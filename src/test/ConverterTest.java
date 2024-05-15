import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void toWrapperArray() {
        int[] intArr = {1, 2, 3, 4};
        long[] longArr = {1_000_000_000_000_000L, 2_000_000_000_000_000L};
        char[] charArr = {'a', 'b', 'c'};
        boolean[] booleanArr = {true, false};
        String[] stringArr = {"this", "Should", "Still", "Work"};
        assertArrayEquals(new Integer[] {1, 2, 3, 4}, Converter.toWrapperArray(intArr));
        assertArrayEquals(new Long[] {1_000_000_000_000_000L, 2_000_000_000_000_000L}, Converter.toWrapperArray(longArr));
        assertArrayEquals(new Character[] {'a', 'b', 'c'}, Converter.toWrapperArray(charArr));
        assertArrayEquals(new Boolean[] {true, false}, Converter.toWrapperArray(booleanArr));
        assertArrayEquals(stringArr, Converter.toWrapperArray(stringArr));
    }
    @Test
    void deepToPrimitiveArray() {
        Integer[][] deepIntArr = {{1, 2}, {3, 4, 5}, {10}};
        assertArrayEquals(new int[][] {{1, 2}, {3, 4, 5}, {10}}, Converter.deepToPrimitiveArray(deepIntArr));
        Integer[][][] deeperIntArr = {{}, {{1}}, {{2, 3}, {4, 5}}};
        assertArrayEquals(new int[][][] {{}, {{1}}, {{2, 3}, {4, 5}}}, Converter.deepToPrimitiveArray(deeperIntArr));
        Integer[][][][] deepestIntArr = {{{{1}, {2}}, {{3}}}, {}, {{{4, 5}}}};
        assertArrayEquals(new int[][][][] {{{{1}, {2}}, {{3}}}, {}, {{{4, 5}}}}, Converter.deepToPrimitiveArray(deepestIntArr));
        Boolean[][][] deepBooleanArr = {{}, {{true}}, {{false, true}, {false, false}}};
        assertArrayEquals(new boolean[][][] {{}, {{true}}, {{false, true}, {false, false}}}, Converter.deepToPrimitiveArray(deepBooleanArr));
        Long[] longArr = {1_000_000_000_000_000L};
        assertArrayEquals(new long[] {1_000_000_000_000_000L}, Converter.deepToPrimitiveArray(longArr));
        double[][][][][] deepDoubleArr = new double[][][][][] {{{{{1.0}, {2.0, 3.0}}}}};
        assertArrayEquals(new double[][][][][] {{{{{1.0}, {2.0, 3.0}}}}}, Converter.deepToPrimitiveArray(deepDoubleArr));
        assertThrows(IllegalArgumentException.class, () -> Converter.deepToPrimitiveArray(1));
    }
    @Test
    void toPrimitiveArray() {
        Integer[] intArr = {1, 2, 3, 4};
        Long[] longArr = {1_000_000_000_000_000L, 2_000_000_000_000_000L};
        Character[] charArr = {'a', 'b', 'c'};
        Boolean[] booleanArr = {true, false};
        int[] intArr2 = {10, 11, 12, 13};
        assertArrayEquals(new int[] {1, 2, 3, 4}, Converter.toPrimitiveArray(intArr));
        assertArrayEquals(new long[] {1_000_000_000_000_000L, 2_000_000_000_000_000L}, Converter.toPrimitiveArray(longArr));
        assertArrayEquals(new char[] {'a', 'b', 'c'}, Converter.toPrimitiveArray(charArr));
        assertArrayEquals(new boolean[] {true, false}, Converter.toPrimitiveArray(booleanArr));
        assertArrayEquals(intArr2, Converter.toPrimitiveArray(intArr2));
        assertArrayEquals(new String[] {"Hello"}, Converter.toPrimitiveArray(new String[] {"Hello"}));
    }
    @Test
    void listToArr() {
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1); listInt.add(2); listInt.add(3);
        assertArrayEquals(new int[] {1, 2, 3}, Converter.listToArr(listInt));
        List<Integer> listOfInt = List.of(3, 2, 1);
        assertArrayEquals(new int[] {3, 2, 1}, Converter.listToArr(listOfInt));

        List<String> listString = new ArrayList<>();
        listString.add("This"); listString.add("is"); listString.add("working");
        assertArrayEquals(new String[] {"This", "is", "working"}, Converter.listToArr(listString));

        List<Boolean> listBoolean = List.of(true, false, true, true);
        assertArrayEquals(new boolean[] {true, false, true, true}, Converter.listToArr(listBoolean));

        List<Long> listLong = new ArrayList<>();
        listLong.add(1_000_000L); listLong.add(1_000_000_000_000L); listLong.add(1_000_000_000_000_000_000L);
        assertArrayEquals(new long[] {1_000_000L, 1_000_000_000_000L, 1_000_000_000_000_000_000L}, Converter.listToArr(listLong));

        List<int[]> listArrInt = new ArrayList<>();
        listArrInt.add(new int[] {1, 2, 3}); listArrInt.add(new int[] {-1000});
        assertArrayEquals(new int[][] {new int[] {1, 2, 3}, new int[] {-1000}}, Converter.listToArr(listArrInt));

        assertThrows(IllegalArgumentException.class, () -> Converter.listToArr(new ArrayList<>()));

        List<Object> listObject = List.of(1, "2", '3', 4L, 5.0f);
        assertArrayEquals(new Object[] {1, "2", '3', 4L, 5.0f}, Converter.listToArr(listObject, Object.class));
    }
    @Test
    void deepListToArr() {
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1); listInt.add(2); listInt.add(3);
        assertArrayEquals(new int[] {1, 2, 3}, Converter.deepListToArr(listInt));
        List<List<Integer>> deepListInt = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(10));
        assertArrayEquals(new int[][] {{1, 2}, {3, 4, 5}, {10}}, Converter.deepListToArr(deepListInt));
        List<List<List<Integer>>> deeperListInt = new ArrayList<>();
        deeperListInt.add(new ArrayList<>());
        deeperListInt.getFirst().add(new ArrayList<>());
        deeperListInt.getFirst().getFirst().add(100);
        assertArrayEquals(new int[][][] {{{100}}}, Converter.deepListToArr(deeperListInt));
        assertThrows(IllegalArgumentException.class, () -> Converter.deepListToArr(new ArrayList<>()));
    }
    @Test
    void digitArray() {
        assertArrayEquals(new int[] {0}, Converter.digitArray(0));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9}, Converter.digitArray(13579));
        assertArrayEquals(new int[] {1, 0, 2, 0, 0, 3, 0, 0, 0, 4, 0, 0, 0, 0}, Converter.digitArray(10200300040000L));
        assertArrayEquals(
            new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            Converter.digitArray(new BigInteger("123456789098765432123456789"))
        );
    }
    @Test
    void convertToBase() {
        assertArrayEquals(new int[] {1, 0, 1}, Converter.convertToBase(5, 2));
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, Converter.convertToBase(12345, 10));
        for (int i = 2; i < 20; i++) {
            assertArrayEquals(new int[] {0}, Converter.convertToBase(0, i));
        }
        assertArrayEquals(new int[] {2, 0, 2}, Converter.convertToBase(100, 7));
    }
    @Test
    void convertFromBase() {
        assertEquals(5, Converter.convertFromBase(new int[] {1, 0, 1}, 2));
        assertEquals(12345, Converter.convertFromBase(new int[] {1, 2, 3, 4, 5}, 10));
        for (int i = 2; i < 20; i++) {
            assertEquals(0, Converter.convertFromBase(new int[] {0}, i));
        }
        assertEquals(100, Converter.convertFromBase(new int[] {2, 0, 2}, 7));
    }
    @Test
    void booleanArrToIntArr() {
        boolean[] arrBoolean = {false, false, false, false, false, false, false, false};
        assertArrayEquals(new int[] {}, Converter.booleanArrToIntArr(arrBoolean));
        assertArrayEquals(new int[] {0, 2}, Converter.booleanArrToIntArr(new boolean[] {true, false, true}));
        assertArrayEquals(new int[] {1}, Converter.booleanArrToIntArr(new boolean[] {false, true, false, false}));
    }
    @Test
    void booleanConversion() {
        boolean[] arrBoolean = {true, false, true, false};
        int[] arrInt = {1, 0, 1, 0};
        assertArrayEquals(arrInt, Converter.booleanConversion(arrBoolean));
        assertArrayEquals(arrBoolean, Converter.booleanConversion(arrInt));
    }
    @Test
    void arrStringToArrInt() {
        String[] arrString = {"1", "10", "0", "0", "0", "1000000000"};
        int[] arrInt = {1, 10, 0, 0, 0, 1_000_000_000};
        assertArrayEquals(arrInt, Converter.arrStringToArrInt(arrString));
        assertArrayEquals(new int[0], Converter.arrStringToArrInt(new String[0]));
    }
    @Test
    void digitFromArrayLong() {
        int[] digits = {1, 2, 3, 4};
        assertEquals(1234, Converter.digitFromArrayLong(digits));
        assertEquals(0, Converter.digitFromArrayLong(new int[0]));
    }
    @Test
    void convertTo() {
        Integer intValue = 42;

        assertEquals(42L, Converter.convertNumberTo(Long.class, intValue));
        assertEquals(42L, Converter.convertNumberTo(long.class, intValue));

        assertEquals(42.0, Converter.convertNumberTo(Double.class, intValue), 0.0001);
        assertEquals(42.0, Converter.convertNumberTo(double.class, intValue), 0.0001);
    }
}