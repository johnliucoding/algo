package coding.pattern.chap8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class TopKTest {

    @Test
    void topKFrequent() {
        var inputs = List.of("go", "coding", "byte", "byte", "go", "interview", "go");
        var expected = List.of("go", "byte");
        List<String> results = TopK.topKFrequent(inputs, 2);
        assertEquals(expected, results);
    }

    @Test
    void topKFrequent2() {
        var inputs = List.of("go", "coding", "byte", "byte", "go", "interview", "go");
        var expected = List.of("go", "byte");
        List<String> results = TopK2.topk(inputs, 2);
        assertEquals(expected, results);
    }
}