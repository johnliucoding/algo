package coding.pattern.chap11;

import java.util.*;

/**
 * @author liuxiaodong02
 */
public class BinaryTreeColumn {

    public static List<List<Integer>> column(TreeNode<Integer> root) {
        if(root == null) {
            return Collections.emptyList();
        }

        record Pair(TreeNode<Integer> node, int column) {}

        // tree map to insure the result in correct order
        TreeMap<Integer, List<Integer>> column_map = new TreeMap<>();

        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(root, 0));


        while(!queue.isEmpty()) {
            Pair polled = queue.poll();
            TreeNode<Integer> node = polled.node;
            int column = polled.column;

            // add the current node's value to its corresponding list in the tree map
            column_map.computeIfAbsent(column, k -> new ArrayList<>()).add(node.val);

            // add the current node's children to the queue with their respective column ids
            if(node.left != null) {
                queue.offer(new Pair(node.left, column + 1));
            }
            if(node.right != null) {
                queue.offer(new Pair(node.right, column + 1));
            }
        }


//        return column_map.values().stream()
//                .map(List::copyOf)
//                .toList();


        return List.copyOf(column_map.values());
    }
}
