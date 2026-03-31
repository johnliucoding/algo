package coding.pattern.chap14;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class SubsetsTest {

    @Test
    void subsets() {
        int[] nums = new int[]{4,5,6};
        List<int[]> result = Subsets.subsets(nums);
        List<int[]> expected = Arrays.asList(new int[]{}, new int[]{4}, new int[]{4,5}, new int[]{4,5,6},
                new int[]{4,6}, new int[]{5}, new int[]{5, 6}, new int[]{6});
        result.sort(Arrays::compare);
        expected.sort(Arrays::compare);

        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), result.get(i));

        }
    }
}