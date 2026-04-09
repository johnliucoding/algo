package recursion.chap4;

import java.util.Optional;

public class TreeSearch {
    static class TreeNode {
        String data;
        TreeNode[] children;

        public TreeNode(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        // individual nodes
        var root = new TreeNode("Alice");
        var node2 = new TreeNode("Bob");
        var node3 = new TreeNode("Caroline");
        var node4 = new TreeNode("Darya");
        var node5 = new TreeNode("Eve");
        var ndoe6 = new TreeNode("Fred");
        var node7 = new TreeNode("Gonzalo");
        var node8 = new TreeNode("Hadassah");

        // relationships
        root.children = new TreeNode[] { node2, node3 };
        node2.children = new TreeNode[] { node4 };
        node3.children = new TreeNode[] { node5, ndoe6 };
        node5.children = new TreeNode[] { node7, node8 };

        Optional<String> nameOptional = find8LetterName(root);
        String name = nameOptional.orElseGet(() -> "no name 8 letters long");
        System.out.println(name);
    }

    public static Optional<String> find8LetterName(TreeNode node) {
        System.out.println("Visiting node " + node.data + " ...");

        // preorder depth-fist seach:
        System.out.println("Checking if " + node.data + " is 8 letters...");
        if(node.data.length() == 8) {
            // BASE CASE
            return Optional.of(node.data);
        }
        if(node.children != null && node.children.length > 0) {
            // recursive case
            for (TreeNode child : node.children) {

                Optional<String> nameOptional = find8LetterName(child);
                if(nameOptional.isPresent()) { // this check is important
                    return nameOptional;
                }

            }
        }
        // postorder depth-first seach:
        // System.out.println("Checking if " + node.data + " is 8 letters...");
        // if (node.data.length() == 8) {
        //     // BASE CASE
        //     return Optional.of(node.data);
        // }

        // value was not found or there are no children
        return Optional.empty();
    }
}
