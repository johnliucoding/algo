package coding.pattern.chap13;

import java.util.ArrayList;
import java.util.Comparator;

public class ConnectDotsMST {


    public static int connect(int[][] points) {
        int n = points.length;
        record Edge(int cost, int i, int j) {}
        var edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int cost = Math.abs(points[i][0] -  points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(cost, i, j));
            }
        }
        edges.sort(Comparator.comparing(Edge::cost));

        var uf = new UnionFind(n);
        var total_cost = 0;
        var edges_added = 0;
        for (var edge : edges) {
            if (uf.union(edge.i, edge.j)) {
                total_cost += edge.cost;
                edges_added++;
                if (edges_added == n-1) {
                    return total_cost;
                }
            }
        }

        return total_cost;
    }
}
