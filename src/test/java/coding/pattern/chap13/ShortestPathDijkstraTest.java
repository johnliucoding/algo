package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class ShortestPathDijkstraTest {

    @Test
    void shortestPath() {
        var n = 6;
        var edges = new int[][]{{0,1,5},{0,2,3},{1,2,1},{1,3,4},{2,3,4},{2,4,5}};
        var start = 0;
        int[] ints = ShortestPathDijkstra.shortestPath(n, edges, start);
        int[] expected = new int[]{0,4,3,7,8,-1};
        assertEquals(expected, ints);
    }
}