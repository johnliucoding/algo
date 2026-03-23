package coding.pattern.chap11;

import java.util.HashMap;
import java.util.Map;

public class BuildBinaryTree {

    static int preorder_index = 0;
    static Map<Integer, Integer> inorder_indexes_map = new HashMap<>();

    public TreeNode<Integer> buildBinaryTree(int[] preorder, int[] inorder) {

        // populate the hash map with the inorder values and their indexes
        for (int i = 0; i < inorder.length; i++) {
            inorder_indexes_map.put(inorder[i], i);
        }
        return buildSubtree(0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode<Integer> buildSubtree(int left, int right, int[] preorder, int[] inorder) {
        // base case: if no elements are in this range, return null
        if (left > right) {
            return null;
        }
        int val = preorder[preorder_index];
        // set 'inorder_index' to the index of the same value pointed at by 'preorder_index'
        int inorder_index = inorder_indexes_map.get(val);
        TreeNode<Integer> node = new TreeNode<>();
        node.val = val;
        // advance 'preorder_index' so it points to the value of the next node to be created
        preorder_index++;
        // build the left and right subtrees and connect them to the current node
        node.left = buildSubtree(left, inorder_index - 1, preorder, inorder);
        node.right = buildSubtree(inorder_index + 1, right, preorder, inorder);
        return node;
    }
}
