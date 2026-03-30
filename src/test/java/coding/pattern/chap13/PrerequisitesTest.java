package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class PrerequisitesTest {

    @Test
    void prerequisites() {

        assertFalse(Prerequisites.prerequisites(new int[][]{{0,1}, {1,2}, {2, 1}}, 3));

        assertTrue(Prerequisites.prerequisites(new int[][]{{0,1}, {0,2}, {3, 2}, {1, 4}, {2, 4}, {4, 5}}, 6));
    }
}