package coding.pattern.chap14;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class PermutationTest {

    @Test
    void permutation() {
        int[] nums = new int[]{4,5,6};
        List<int[]> result = Permutation.permutation(nums);
        List<int[]> expected = Arrays.asList(new int[]{4,5,6}, new int[]{4,6,5}, new int[]{5,4,6}, new int[]{5,6,4},
                new int[]{6,4,5}, new int[]{6,5,4});
        result.sort(Arrays::compare);
        expected.sort(Arrays::compare);

        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), result.get(i));

        }
    }
}