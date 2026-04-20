package recursion.chap4;

public class TreeDepth {
    public static class TreeNode {
        String data;
        TreeNode[] children;

        public TreeNode(String data) {
            this.data = data;
        }
    }

    static void main(String[] args) {
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

        int depth = getDepth(root);
        System.out.println(depth);
    }

    public static int getDepth(TreeNode node) {

        if(node.children == null || node.children.length == 0) {
            // BASE CASE
            return 0;
        } else {
            // RECURSIVE CASE
            var maxDepth = 0;
            for (TreeNode child : node.children) {
                var childDepth = getDepth(child);
                if(childDepth > maxDepth) {
                    maxDepth = childDepth;
                }
            }

            return maxDepth + 1;
        }
        
    }

}
