package tree.traversal;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/12/30 10:47 AM
 */
public class BreadthFirstTraversal {

    public static void traverseLevelOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        traverseLevelOrder(tree.root(), visitor);
    }

    private static void traverseLevelOrder(TreeNode root, Consumer<TreeNode> visitor) {
        if (root == null) {
            return;
        }
        final Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            visitor.accept(node);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
