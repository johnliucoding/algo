package coding.pattern.chap18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HammingWeightsTest {

    @Test
    void hammingWeights() {

        int[] ints = HammingWeights.hammingWeights(7);
        int[] expect = {0,1,1,2,1,2,2,3};
        assertArrayEquals(expect, ints);
        int[] ints2 = HammingWeights.hammingWeightsDp(7);
        assertArrayEquals(expect, ints2);
    }
}