import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComparerTest {

    @Test
    void compareTo() {
        assertEquals(1, Comparer.compareTo(2, 1));
        assertEquals(1, Comparer.compareTo(1_000_000_000_000L, 500_000_000_000L));
        assertEquals(-1, Comparer.compareTo(2.5, 2.7));
        assertEquals(1, Comparer.compareTo(3.15f, 3.14f));
        assertEquals(1, Comparer.compareTo(true, false));
        assertEquals(-1, Comparer.compareTo((byte) 0, (byte) 1));
        assertEquals(-16, Comparer.compareTo((short) 16, (short) 32));
        assertEquals(-1, Comparer.compareTo('a', 'b'));
        assertEquals(-15, Comparer.compareTo("hello", "world"));
        assertThrows(IllegalArgumentException.class, () -> Comparer.compareTo(new Object(), new Object()));
        assertThrows(IllegalArgumentException.class, () -> Comparer.compareTo(2, "o"));
    }
}