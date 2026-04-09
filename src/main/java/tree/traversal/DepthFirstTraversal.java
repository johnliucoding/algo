package tree.traversal;

import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/12/30 10:51 AM
 */
public class DepthFirstTraversal {

    public static void traversePreOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        traversePreOrder(tree.root(), visitor);
    }

    private static void traversePreOrder(TreeNode node, Consumer<TreeNode> visitor) {
        if (node == null) {
            return;
        }
        visitor.accept(node);
        traversePreOrder(node.left, visitor);
        traversePreOrder(node.right, visitor);
    }

    public static void traversePostOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        traversePostOrder(tree.root(), visitor);
    }

    private static void traversePostOrder(TreeNode node, Consumer<TreeNode> visitor) {
        if (node == null) {
            return;
        }
        traversePostOrder(node.left, visitor);
        traversePostOrder(node.right, visitor);
        visitor.accept(node);
    }

    public static void traverseInOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        traverseInOrder(tree.root(), visitor);
    }

    private static void traverseInOrder(TreeNode node, Consumer<TreeNode> visitor) {
        if (node == null) {
            return;
        }
        traverseInOrder(node.left, visitor);
        visitor.accept(node);
        traverseInOrder(node.right, visitor);

    }

    public static void traverseReverseInOrder(BinaryTree tree, Consumer<TreeNode> visitor) {
        traverseReverseInOrder(tree.root(), visitor);
    }

    private static void traverseReverseInOrder(TreeNode node, Consumer<TreeNode> visitor) {
        if (node == null) {
            return;
        }
        traverseReverseInOrder(node.right, visitor);
        visitor.accept(node);
        traverseReverseInOrder(node.left, visitor);

    }
}
