package coding.pattern.chap11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class TreeSerializer {

    public static String serialize(TreeNode<Integer> root) {
        // perform a preorder traversal to add node values to a list, then
        // convert the list to a string
        ArrayList<String> list = new ArrayList<>();
        preorder_serialize(root, list);
        return String.join(",", list);
    }

    private static void preorder_serialize(TreeNode<Integer> node, ArrayList<String> list) {
        // base case: mark null nodes as '#'
        if (node == null) {
            list.add("#");
            return;
        }
        // preorder traversal processes the current node first, then the left and right children
        list.add(node.val.toString());
        preorder_serialize(node.left, list);
        preorder_serialize(node.right, list);
    }
    public static TreeNode<Integer> deserialize(String data) {
        // obtain the node values by splitting the string using the comma delimiter
        List<String> node_values = Arrays.asList(data.split(","));

        return build_tree(node_values);
    }

    static int position = 0; // 不能通过参数传递，每个栈都有自己的copy, 在这个栈里++后，返回，会导致++失效
    private static TreeNode<Integer> build_tree(List<String> node_values) {
        String val = node_values.get(position);
        position++;
        // base case: '#' indicates a null node
        if(val.equals("#")) {
            return null;
        }
        TreeNode<Integer> node = new TreeNode<>();
        node.val = Integer.valueOf(val);
        node.left = build_tree(node_values);
        node.right = build_tree(node_values);
        return node;
    }

    // recursive function 要考虑的

    // parameter参数->不断变小
    // 返回值，如果在递归返回后用到这个值要考虑返回什么合适
    // global/context ->  state 类似上面的position ， @see BuildBinaryTree
    //                    value 类似 @see LowestCommonAncestor MaxSumOfContinuousPath
}
