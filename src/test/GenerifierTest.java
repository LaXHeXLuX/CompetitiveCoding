import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class GenerifierTest {

    @Test
    void compareTo() {
        assertEquals(1, Generifier.compareTo(2, 1));
        assertEquals(1, Generifier.compareTo(1_000_000_000_000L, 500_000_000_000L));
        assertEquals(-1, Generifier.compareTo(2.5, 2.7));
        assertEquals(1, Generifier.compareTo(3.15f, 3.14f));
        assertEquals(1, Generifier.compareTo(true, false));
        assertEquals(-1, Generifier.compareTo((byte) 0, (byte) 1));
        assertEquals(-16, Generifier.compareTo((short) 16, (short) 32));
        assertEquals(-1, Generifier.compareTo('a', 'b'));
        assertEquals(-15, Generifier.compareTo("hello", "world"));
        assertEquals(-1, Generifier.compareTo(BigInteger.ZERO, BigInteger.TWO));
    }
}