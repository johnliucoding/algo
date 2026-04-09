package goodrich.ch8.traversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/8/29 4:06 PM
 */
public class BreadthFirst {

    public static void breadthFirst(TreeNode root, Consumer<TreeNode> action) {
        if (root == null) {
            return;
        }
        final Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            action.accept(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
