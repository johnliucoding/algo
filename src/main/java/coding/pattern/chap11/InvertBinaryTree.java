package coding.pattern.chap11;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvertBinaryTree {

    public static <E> TreeNode<E> invertBinaryTree(TreeNode<E> root) {
        // base case: if the node is null, there's nothing to invert
        if (root == null) {
            return null;
        }
        // swap the left and right subtrees of the current node
        var temp = root.left;
        root.left = root.right;
        root.right = temp;
        // recursively invert the left and right subtree
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
        return root;
    }

    public static <E> TreeNode<E> invertBinaryTreeIterative(TreeNode<E> root) {
        if (root == null) {
            return null;
        }
        var stack = new ArrayDeque<TreeNode<E>>();
        stack.push(root);
        while (!stack.isEmpty()) {
            var node = stack.pop();
            // swap the left and right subtrees of the current node
            var temp = node.left;
            node.left = node.right;
            node.right = temp;
            // push the left and right subtrees onto the stack
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        return root;
    }
}
