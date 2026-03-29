package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BipartiteGraphTest {

    @Test
    void isBipartite() {
        var graph = List.of(
          List.of(1, 4),
          List.of(0, 2),
          List.of(1),
          List.of(4),
          List.of(0, 3)
        );
        assertTrue(BipartiteGraph.isBipartite(graph));
    }
}