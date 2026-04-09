package ch8.traversal;

import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/8/29 4:03 PM
 */
public class PostOrder {

    public static void postorder(TreeNode root, Consumer<TreeNode> action) {
        if (root == null) {
            return;
        }
        postorder(root.left, action);
        postorder(root.right, action);
        action.accept(root);
    }
}
