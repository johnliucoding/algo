package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class ShortestTransformationSequenceTest {

    @Test
    void shortestPath() {
        String start = "red";
        String end = "hit";
        List<String> dict = List.of("red", "bed", "hat", "rod", "rad", "rat", "hit", "bad", "bat");
        int result = ShortestTransformationSequence.shortestPath(start, end, dict);
        assertEquals(5, result);
    }

    @Test
    void shortestPathBi() {
        String start = "red";
        String end = "hit";
        List<String> dict = List.of("red", "bed", "hat", "rod", "rad", "rat", "hit", "bad", "bat");
        int result = ShortestTransformationSequenceBiDirection.shortestPath(start, end, dict);
        assertEquals(5, result);
    }
}