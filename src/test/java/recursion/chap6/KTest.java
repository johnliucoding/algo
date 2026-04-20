package recursion.chap6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class KTest {

    private static final Logger log = LoggerFactory.getLogger(KTest.class);

    @Test
    void testCombination() {

        int[] ints = {1,2,3,4,5};
        int k = 3;

        List<int[]> kCombinations = KCombination.combine(ints, k);

        log.info("kCombinations: {}, size: {}", str(kCombinations), kCombinations.size());

    }

    @Test
    void testPermuation() {
        int[] ints = {1,2,3,4,5};
        int k = 3;
        List<int[]> kPermutations = KPermutation.permute(ints, k);
        log.info("kPermutations: {}, size: {}", str(kPermutations),  kPermutations.size());
        List<int[]> kPermutationDup = KPermutationDup.permute(ints, k);
    }

    @Test
    void testPermuationDup() {
        int[] ints = {1,2,3,4,5};
        int k = 3;

        List<int[]> kPermutationDup = KPermutationDup.permute(ints, k);
        log.info("kPermutationDup: {}, size: {}", str(kPermutationDup),  kPermutationDup.size());
    }

    private String str(List<int[]> list) {
        return list.stream()
                .map(Arrays::toString)
                .collect(Collectors.joining(", ", "[ ", " ]"));
    }
}