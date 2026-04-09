package goodrich.ch8.traversal;

import java.util.function.Consumer;

/**
 * @author Liu Xiaodong
 * @since 2024/8/29 4:00 PM
 */
public class PreOrder {

    public static void preorder(TreeNode root, Consumer<TreeNode> action) {
        if (root == null) {
            return;
        }
        action.accept(root);
        preorder(root.left, action);
        preorder(root.right, action);
    }
}
