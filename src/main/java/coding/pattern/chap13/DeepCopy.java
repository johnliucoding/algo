package coding.pattern.chap13;

import java.util.HashMap;
import java.util.Map;

public class DeepCopy {

    public static <T> GraphNode<T> copy(GraphNode<T> node) {
        if (node == null) {
            return null;
        }
        HashMap<GraphNode<T>, GraphNode<T>> map = new HashMap<>();
        return dfs(node, map);
    }

    private static <T> GraphNode<T> dfs(GraphNode<T> node, Map<GraphNode<T>, GraphNode<T>> map) {
        // if this node was already cloned, then return this previously cloned node
        if(map.containsKey(node)) {
            return map.get(node);
        }
        // clone the current node
        GraphNode<T> clonedNode = new GraphNode<>(node.value);
        // store the current node to ensure it doesn't need to be created again in future DFS calls
        map.put(node, clonedNode);
        // iterate through the neighbors of the current node to connect their clones to the
        // current cloned node.
        for (GraphNode<T> neighbor : node.neighbors) {
            GraphNode<T> clonedNeighbor = dfs(neighbor, map);
            clonedNode.neighbors.add(clonedNeighbor);
        }
        return clonedNode;
    }


}
