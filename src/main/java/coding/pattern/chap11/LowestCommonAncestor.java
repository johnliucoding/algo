package coding.pattern.chap11;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author liuxiaodong02
 */
public class LowestCommonAncestor {

    private static TreeNode<?> lca;

    public static TreeNode<?> lca(TreeNode<?> root, TreeNode<?> p, TreeNode<?> q) {
        dfs(root, p, q);
        return lca;
    }

    private static boolean dfs(TreeNode<?> node, TreeNode<?> p, TreeNode<?> q) {
        // base case: a null node is neither 'p' or 'q'
        if(node == null) {
            return false;
        }

        boolean node_is_p_or_q = node == p || node == q;
        // recursively determine if the left and right subtrees contain 'p' or 'q'
        boolean left_contains_p_or_q = dfs(node.left, p, q);
        boolean right_contains_p_or_q = dfs(node.right, p, q);

        // if two of the above three variables are true, the current node is the LCA
        long how_many_true = Stream.of(
                        node_is_p_or_q,
                        left_contains_p_or_q,
                        right_contains_p_or_q)
                .filter(Boolean::booleanValue)
                .count();
        if(how_many_true == 2) {
            lca = node;
        }

        // return true if the current subtree contains 'p' or 'q'
        return how_many_true == 1;
    }
}
