package coding.pattern.chap11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class TreeSerializerTest {

    @Test
    void test() {
        String data = "5,9,2,11,#,#,#,#,3,4,#,6,#,#,7,#,#";
        TreeNode<Integer> tree = TreeSerializer.deserialize(data);
        String result = TreeSerializer.serialize(tree);
        assertEquals(data, result);
    }
}