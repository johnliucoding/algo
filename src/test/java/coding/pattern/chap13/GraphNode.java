package coding.pattern.chap13;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    public T value;
    public List<GraphNode<T>> neighbors;
    public GraphNode(T value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }
}
