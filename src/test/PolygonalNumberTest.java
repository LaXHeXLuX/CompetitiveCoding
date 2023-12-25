import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PolygonalNumberTest {

    @Test
    void polygonalNumberLong() {
        for (int i = 1; i < 10; i++) {
            assertEquals(1, PolygonalNumber.polygonalNumberLong(i, 1));
            assertEquals(i, PolygonalNumber.polygonalNumberLong(i, 2));
        }
        assertEquals(10, PolygonalNumber.polygonalNumberLong(3, 4));
        assertEquals(370, PolygonalNumber.polygonalNumberLong(10, 10));
        assertEquals(500051, PolygonalNumber.polygonalNumberLong(101, 101));
    }

    @Test
    void polygonalNumberBigInteger() {
        for (int i = 1; i < 10; i++) {
            assertEquals(1, PolygonalNumber.polygonalNumberLong(i, 1));
            assertEquals(i, PolygonalNumber.polygonalNumberLong(i, 2));
        }
        assertEquals(new BigInteger("498999502000000"), PolygonalNumber.polygonalNumberBigInteger(1_000, 1_000_000));
        assertEquals(new BigInteger("11470911303321"), PolygonalNumber.polygonalNumberBigInteger(7_777, 54_321));
    }

    @Test
    void isPolygonalNumber() {
        assertArrayEquals(new int[] {8}, PolygonalNumber.isPolygonalNumber(40));
        assertArrayEquals(new int[] {}, PolygonalNumber.isPolygonalNumber(101));
        assertArrayEquals(new int[] {8, 15, 48}, PolygonalNumber.isPolygonalNumber(280));
        assertArrayEquals(new int[] {19, 66, 601}, PolygonalNumber.isPolygonalNumber(1_800));
        assertArrayEquals(new int[] {33_334}, PolygonalNumber.isPolygonalNumber(99_999));
    }
}