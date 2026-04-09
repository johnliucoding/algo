package tree.traversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/12/30 11:07 AM
 */
public class DepthFirstTraversalIterative {

    public static void traversePreOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        TreeNode node = tree.root();
        if (node == null) {
            return;
        }

        final ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            visitor.accept(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void traversePostOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        TreeNode node = tree.root();
        TreeNode lastVisitedNode = null;

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                final TreeNode topNode = stack.peek();
                if (topNode.right != null && topNode.right != lastVisitedNode) {
                    node = topNode.right;
                } else {
                    visitor.accept(topNode);
                    lastVisitedNode = stack.poll();
                }
            }
        }
    }

    public static void traverseInOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        TreeNode node = tree.root();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                visitor.accept(node);
                node = node.right;
            }
        }
    }

    public static void traverseReverseInOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        TreeNode node = tree.root();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                visitor.accept(node);
                node = node.left;
            }
        }
    }
}
