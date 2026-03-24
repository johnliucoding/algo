package coding.pattern.chap11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class BSTKthSmallest {

    public static int kthSmallest(TreeNode<Integer> root, int k) {
        List<Integer> inorderList = inorder(root);
        return inorderList.get(k - 1);
    }

    // inorder traversal function to attain a sorted list of nodes from the BST
    private static List<Integer> inorder(TreeNode<Integer> node) {
        if (node == null) {
            return Collections.emptyList();
        }
        ArrayList<Integer> list = new ArrayList<>(inorder(node.left));
        list.add(node.val);
        list.addAll(inorder(node.right));
        return list;
    }

    public static int kthSmallestIterative(TreeNode<Integer> root, int k) {
        var stack = new ArrayDeque<TreeNode<Integer>>();
        var node = root;
        while(!stack.isEmpty() || node != null) {
            // move to the leftmost node and add nodes to the stack as we go so they
            // can be processed in future iterations
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            // pop the top node from the stack to process it, decrement 'k'
            node = stack.pop();
            k--;
            // if we have processed 'k' nodes, return the value of the 'k' th smallest
            if(k == 0) {
                return node.val;
            }
            // move to the right subtree
            node = node.right;
        }
        return -1;
    }
}
