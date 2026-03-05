package coding.pattern.chap2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class GeometricSequenceTripletsTest {

    @Test
    void testTriplets() {
        int[] nums = {2, 1, 2, 4, 8, 8};
        int ration = 2;

        int result1 = GeometricSequenceTriplets.bruteForceTriplets(nums, ration);
        int result2 = GeometricSequenceTriplets.bruteForceTriplets2(nums, ration);
        int result3 = GeometricSequenceTriplets.mapCase(nums, ration);

        assertEquals(5, result1);
        assertEquals(5, result2);
        assertEquals(5, result3);
    }

}