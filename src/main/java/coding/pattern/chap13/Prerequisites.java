package coding.pattern.chap13;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class Prerequisites {

    public static boolean prerequisites(int[][] list, int n) {
        var graph = new HashMap<Integer, List<Integer>>();
        var inDegrees = new int[n];

        // represent the graph as an adjacency list and record the in-degree of each course
        for (int[] pair : list) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            inDegrees[pair[1]]++;
        }

        var queue = new ArrayDeque<Integer>();
        // add all courses with in-degree of 0 to the queue
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        var enrolled = 0;
        // perform topological sort
        while (!queue.isEmpty()) {
            int node = queue.poll();
            enrolled++;
            for (int neighbor : graph.computeIfAbsent(node, k -> new ArrayList<>())) {
                inDegrees[neighbor]--;
                // if the in-degree of a neighbor course becomes 0, add it the queue
                if (inDegrees[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        // return true if we've successfully enrolled in all courses
        return enrolled == n;
    }
}
