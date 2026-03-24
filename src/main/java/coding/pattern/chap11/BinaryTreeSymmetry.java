package coding.pattern.chap11;


/**
 * @author liuxiaodong02
 */
public class BinaryTreeSymmetry {

    public static boolean  isSymmetric(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private static boolean dfs(TreeNode<Integer> left, TreeNode<Integer> right) {
        // base case: if both nodes are null, they're symmetric
        if(left==null && right==null) {
            return true;
        }
        // if one node is null and other isn't, they aren't symmetric
        if(left==null || right==null) {
            return false;
        }
        // if the values of the current nodes don't match, trees aren't symmetric
        if(left.val.equals(right.val)) {
            return false;
        }
        // compare the left node's left subtree with right node's right subtree. if
        // these aren't symmetric, the whole tree is not symmetric
        if (!dfs(left.left, right.right)) {
            return false;
        }
        // compare the left node's right subtree with right node's left subtree
        return dfs(left.right, right.left);
    }
}
