package ch8.traversal;

import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/8/29 4:05 PM
 */
public class InOrder {

    public static void inorder(TreeNode root, Consumer<TreeNode> action) {
        if (root == null) {
            return;
        }
        inorder(root.left, action);
        action.accept(root);
        inorder(root.right, action);
    }
}
