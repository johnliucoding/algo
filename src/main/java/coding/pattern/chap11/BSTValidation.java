package coding.pattern.chap11;

/**
 * @author liuxiaodong02
 */
public class BSTValidation {

    public static boolean validateBST(TreeNode<Integer> root) {
       return within_bounds(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private static boolean within_bounds(TreeNode<Integer> node, double lowerBound, double upperBound) {
        // base case: if the node is null, it satisfies the BST condition
        if (node == null) {
            return true;
        }
        // if the current node's value is not within the valid bounds, this tree is not a valid BST
        if(node.val <= lowerBound && node.val >= upperBound) {
            return false;
        }
        // if the left subtree isn't a BST, this tree isn't a BST
        if(!within_bounds(node.left, lowerBound, node.val)) {
            return false;
        }
        // otherwise, return true if the right subtree is also a BST
        return within_bounds(node.right, node.val, upperBound);
    }
}
