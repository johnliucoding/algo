package coding.pattern.chap13;

import java.util.ArrayList;
import java.util.List;

public class BipartiteGraph {

    static public boolean isBipartite(List<List<Integer>> graph) {
        var colors = new ArrayList<Integer>();
        for (int i = 0; i < graph.size(); i++) {
            colors.add(0);
        }

        // determine if each graph component is bipartite
        for (int i = 0; i < graph.size(); i++) {
            if(colors.get(i) == 0 &&
                !dfs(i, 1, graph, colors)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int i, int color, List<List<Integer>> graph, ArrayList<Integer> colors) {
        colors.set(i, color);
        for (int neighbor : graph.get(i)) {
            // if the current neighbor has the same color as the current node,
            // the graph is not bipartite
            if(colors.get(neighbor) == color) {
                return false;
            }
            // if the current neighbor is not colored, color it with the
            // other color and continue the DFS
            if(colors.get(neighbor) == 0 &&
                !dfs(neighbor, -color, graph, colors)) {
                return false;
            }
        }
        return true;
    }
}
