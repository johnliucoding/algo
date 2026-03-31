package coding.pattern.chap14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class NQueenTest {

    @Test
    void nQueen() {
        assertEquals(2, NQueen.nQueen(4));
    }
}