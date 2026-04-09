package recursion.chap4;



public class TreeTraverse {
    
    static class TreeNode {
        String data;
        TreeNode[] children;
        public TreeNode(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        // individual nodes
        var root = new TreeNode("A");
        var node2 = new TreeNode("B");
        var node3 = new TreeNode("C");
        var node4 = new TreeNode("D");
        var node5 = new TreeNode("E");
        var ndoe6 = new TreeNode("F");
        var node7 = new TreeNode("G");
        var node8 = new TreeNode("H");

        // relationships
        root.children = new TreeNode[] { node2, node3 };
        node2.children = new TreeNode[] { node4 };
        node3.children = new TreeNode[] { node5, ndoe6 };
        node5.children = new TreeNode[] { node7, node8 };


        
        preorderTraverse(root);
        System.out.println();

        postorderTraverse(root);
        System.out.println();

        inorderTraverse(root);
        System.out.println();
    }



    public static void preorderTraverse(TreeNode node) {
        System.out.print(node.data + " ");

        if(node.children != null && node.children.length > 0) {
            // RECURSIVE CASE
            for (TreeNode child : node.children) {
                // traverse child nodes
                preorderTraverse(child);
            }
        }
        // BASE CASE
    }

    public static void postorderTraverse(TreeNode node) {
        if (node.children != null && node.children.length > 0) {
            // RECURSIVE CASE
            for (TreeNode child : node.children) {
                // traverse child nodes
                postorderTraverse(child);
            }
        }

        System.out.print(node.data + " ");
        // BASE CASE
    }

    public static void inorderTraverse(TreeNode node) {
        if(node.children != null && node.children.length >= 1) {
            // recursive case
            inorderTraverse(node.children[0]); // traverse the left child.
        }
        System.out.print(node.data + " ");     // access this node's data.
        if(node.children != null && node.children.length >= 2) {
            // recursive case
            inorderTraverse(node.children[1]); // traverse the right child.
        }
        // base case

    }
}
