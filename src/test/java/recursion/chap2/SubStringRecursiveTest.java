package recursion.chap2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class SubStringRecursiveTest {

    @Test
    void findSubStringRecursive() {
        int errIdx =SubStringRecursive.findSubString("cat", "ca");
        assertEquals(-1, errIdx);
        int err2Idx =SubStringRecursive.findSubString("cat", "hello ca");
        assertEquals(-1, err2Idx);
        int start =SubStringRecursive.findSubString("cat", "cat");
        assertEquals(0, start);
        int idx = SubStringRecursive.findSubString("cat", "My cat Zophie");
        assertEquals(3, idx);
    }

    @Test
    void findSubStringIterative() {
        int errIdx =SubStringIterative.findSubString("cat", "ca");
        assertEquals(-1, errIdx);
        int err2Idx =SubStringIterative.findSubString("cat", "hello ca");
        assertEquals(-1, err2Idx);
        int start =SubStringIterative.findSubString("cat", "cat");
        assertEquals(0, start);
        int idx = SubStringIterative.findSubString("cat", "My cat Zophie");
        assertEquals(3, idx);
    }
}