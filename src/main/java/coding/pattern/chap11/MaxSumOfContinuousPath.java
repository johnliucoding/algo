package coding.pattern.chap11;

public class MaxSumOfContinuousPath {
    static int max_sum = Integer.MIN_VALUE;
    static public int max_path_sum(TreeNode<Integer> root) {
        max_path_sum_helper(root);
        return max_sum;
    }
    private static int max_path_sum_helper(TreeNode<Integer> node) {
        // base case: null nodes have no path sum
        if(node == null) return 0;
        // collect the maximum gain we can attain from the left and right subtree,
        // setting them to 0 if they're negative
        int left_sum = Math.max(max_path_sum_helper(node.left), 0);
        int right_sum = Math.max(max_path_sum_helper(node.right), 0);
        // update the overall maximum path sum if the current path sum is larger
        max_sum = Math.max(max_sum, node.val + left_sum + right_sum);
        // return the maximum sum of a single, continuous path with the current node as an endpoint
        return node.val + Math.max(left_sum, right_sum);
    }

}
