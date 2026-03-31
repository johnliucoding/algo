package coding.pattern.chap14;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class CombinationTest {

    @Test
    void combination() {
        int[] nums = new int[]{1,2,3};
        int target = 4;
        List<int[]> result = Combination.combination(nums, target);


        List<int[]> expected = Arrays.asList(new int[]{1,1,1,1}, new int[]{1,1,2}, new int[]{1,3}, new int[]{2,2});
        result.sort(Arrays::compare);
        expected.sort(Arrays::compare);

        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), result.get(i));

        }
    }
}