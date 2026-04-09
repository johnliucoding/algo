package tree.traversal;

/**
 * @author Liu Xiaodong
 * @since 2024/12/28 12:29 AM
 */
public class BSTree implements BinaryTree {

    private TreeNode root;

    @Override
    public TreeNode root() {
        return root;
    }

    public TreeNode searchNode(int key) {
        return searchNode(key, root);
    }

    private TreeNode searchNode(int key, TreeNode node) {
        if (node == null) {
            return null;
        }

        if (key == node.data) {
            return node;
        } else if (key < node.data) {
            return searchNode(key, node.left);
        } else {
            return searchNode(key, node.right);
        }
    }

    public TreeNode searchNodeIterative(int key) {
        TreeNode node = root;
        while (node != null) {
            if (key == node.data) {
                return node;
            } else if (key < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public void insertNode(int key) {
        root = insertNode(key, root);
    }

    private TreeNode insertNode(int key, TreeNode node) {
        // No node at current position --> store new node at current position
        if (node == null) {
            node = new TreeNode(key);
        }

        // Otherwise, traverse the tree to the left or right depending on the key
        else if (key < node.data) {
            node.left = insertNode(key, node.left);
        } else if (key > node.data) {
            node.right = insertNode(key, node.right);
        } else {
            throw new IllegalStateException("BST already contains a node with key " + key);
        }

        return node;
    }

    public void insertNodeIterative(int key) {

        TreeNode node = root;
        TreeNode parent = null;
        // Traverse the tree to the left or right depending on the key
        while (node != null) {
            parent = node;
            if (key < node.data) {
                node = node.left;
            } else if (key > node.data) {
                node = node.right;
            } else {
                throw new IllegalStateException("BST already contains a node with key " + key);
            }
        }
        // Insert new node
        TreeNode newNode = new TreeNode(key);

        if (parent == null) {
            root = newNode;
        } else if (key < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public void deleteNode(int key) {
        root = deleteNode(key, root);
    }

    private TreeNode deleteNode(int key, TreeNode node) {
        // No node at current position --> go up the recursion
        if (node == null) {
            return null;
        }

        // Traverse the tree to the left or right depending on the key
        if (key < node.data) {
            node.left = deleteNode(key, node.left);
        } else if (key > node.data) {
            node.right = deleteNode(key, node.right);
        }

        // At this point, "node" is the node to be deleted

        // Node has no children --> just delete it
        else if (node.left == null && node.right == null) {
            node = null;
        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildren(node);
        }

        return node;
    }

    private void deleteNodeWithTwoChildren(TreeNode node) {
        // Find minimum node of right subtree ("inorder successor" of current node)
        TreeNode inOrderSuccessor = findMinimum(node.right);

        // Copy inorder successor's data to current node
        node.data = inOrderSuccessor.data;

        // Delete inorder successor recursively
        node.right = deleteNode(inOrderSuccessor.data, node.right);
    }

    private TreeNode findMinimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void deleteNodeIterative(int key) {
        TreeNode node = root;
        TreeNode parent = null;

        // Find the node to be deleted
        while (node != null && node.data != key) {
            // Traverse the tree to the left or right depending on the key
            parent = node;
            if (key < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // Node not found?
        if (node == null) {
            return;
        }

        // At this point, "node" is the node to be deleted

        // Node has at most one child --> replace node by its single child
        if (node.left == null || node.right == null) {
            deleteNodeWithZeroOrOneChild(key, node, parent);
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildrenIterative(node);
        }
    }

    private void deleteNodeWithZeroOrOneChild(int key, TreeNode node, TreeNode parent) {
        TreeNode singleChild = node.left != null ? node.left : node.right;

        if (node == root) {
            root = singleChild;
        } else if (key < parent.data) {
            parent.left = singleChild;
        } else {
            parent.right = singleChild;
        }
    }

    private void deleteNodeWithTwoChildrenIterative(TreeNode node) {
        // Find minimum node of right subtree ("inorder successor" of current node)
        TreeNode inOrderSuccessor = node.right;
        TreeNode inOrderSuccessorParent = node;
        while (inOrderSuccessor.left != null) {
            inOrderSuccessorParent = inOrderSuccessor;
            inOrderSuccessor = inOrderSuccessor.left;
        }

        // Copy inorder successor's data to current node
        node.data = inOrderSuccessor.data;

        // Delete inorder successor

        // Case a: Inorder successor is the deleted node's right child
        if (inOrderSuccessor == node.right) {
            // --> Replace right child with inorder successor's right child
            node.right = inOrderSuccessor.right;
        }

        // Case b: Inorder successor is further down, meaning, it's a left child
        else {
            // --> Replace inorder successor's parent's left child
            //     with inorder successor's right child
            inOrderSuccessorParent.left = inOrderSuccessor.right;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        appendNodeToStringRecursive(root(), builder);
        return builder.toString();
    }

    private void appendNodeToStringRecursive(TreeNode node, StringBuilder builder) {
        appendNodeToString(node, builder);
        if (node.left != null) {
            builder.append(" L{");
            appendNodeToStringRecursive(node.left, builder);
            builder.append('}');
        }
        if (node.right != null) {
            builder.append(" R{");
            appendNodeToStringRecursive(node.right, builder);
            builder.append('}');
        }
    }

    protected void appendNodeToString(TreeNode node, StringBuilder builder) {
        builder.append(node.data);
    }
}
