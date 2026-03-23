package coding.pattern.chap11;

/**
 * @author liuxiaodong02
 *
 * postorder traverse to get height
 */
public class BalancedTreeValidation {

    public static <E> boolean isBalanced(TreeNode<E> root) {
        return get_height_imbalanced(root) != -1;
    }

    private static <E> int get_height_imbalanced(TreeNode<E> root) {
        // base case: if the node is null, its height is 0
        if(root == null) return 0;
        // recursively get the height of the left and right subtrees
        // if either subtree is imbalanced, propagate -1 up the tree
        int left_height = get_height_imbalanced(root.left);
        int right_height = get_height_imbalanced(root.right);
        if(left_height == -1 || right_height == -1) return -1;
        // if the current node's subtree is imbalanced
        // (height difference > 1), return -1
        if(Math.abs(left_height - right_height) > 1) return -1;
        // return the height of the current subtree
        return Math.max(left_height, right_height) + 1;
    }
}
