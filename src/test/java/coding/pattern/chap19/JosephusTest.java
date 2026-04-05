package coding.pattern.chap19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JosephusTest {

    @Test
    void josephus() {

        assertEquals(2, Josephus.josephus(5, 2));
        assertEquals(2, Josephus.josephusOptimized(5, 2));
    }
}