package coding.pattern.chap13;

import java.util.*;

/**
 * @author liuxiaodong02
 */
public class ShortestPathDijkstra {

    public static int[] shortestPath(int n, int[][] edges, int start) {
        var graph = new HashMap<Integer, List<int[]>>(); // from -> list[(to1, weight), (to2, weight) ]
        var distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        // represent the graph as an adjacency list
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int wight = edge[2];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, wight});
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(new int[]{from, wight});
        }
        record Pair(int distance, int node) {}
        var minHeap = new PriorityQueue<Pair>(Comparator.comparing(Pair::distance));
        minHeap.offer(new Pair(0, start));

        // use Dijkstra's algorithm to find the shortest path between the start node and all other nodes
        while (!minHeap.isEmpty()) {
            Pair polled = minHeap.poll();
            int current_distance = polled.distance;
            int current_node = polled.node;
            // if the current distance to this node is greater than the recorded distance,
            // we've already found the shortest distance to this node.
            if(current_distance > distances[current_node]) {
                continue;
            }
            // update the distances of the neighboring nodes
            for (int[] ints : graph.get(current_node)) {
                int neighbor = ints[0];
                int weight = ints[1];
                int neighbor_distance = current_distance  + weight;
                // only update the distance if we find a shorter path to this neighbor
                if(neighbor_distance < distances[neighbor]) {
                    distances[neighbor] = neighbor_distance;
                    minHeap.offer(new Pair(neighbor_distance, neighbor));
                }
            }



        }
        // convert all MAX_INT to -1, representing unreachable nodes
        for (int i = 0; i < distances.length; i++) {
            if(distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }

        return distances;
    }
}
