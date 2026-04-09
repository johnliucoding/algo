package tree;

/**
 * balanced binary search tree using avl tree
 * @author Liu Xiaodong
 * @since 2024/12/27 4:14 PM
 */
public class AVLTree {

    public static class Node {
        int data;
        Node left;
        Node right;
        int height;
        public Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    private int height(Node node) {
        return node != null ? node.height : -1;
    }

    private void updateHeight(Node node) {
        int leftH = height(node.left);
        int leftR = height(node.right);
        node.height = Math.max(leftH, leftR) + 1;
    }

    private int balanceFactor(Node node) {
        if(node == null) {
            return 0;
        }
        return height(node.right) - height(node.left);
    }

    private Node rotateRight(Node node) {
        Node l = node.left;

        node.left = l.right;
        l.right = node;

        updateHeight(node);
        updateHeight(l);

        return l;
    }

    private Node rotateLeft(Node node) {
        Node r = node.right;

        node.right = r.left;
        r.left = node;

        updateHeight(node);
        updateHeight(r);

        return r;
    }

    private Node rebalance(Node node) {
        int bf = balanceFactor(node);

        if(bf < -1) {
            if(balanceFactor(node.left) <= 0) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        if(bf > 1) {
            if(balanceFactor(node.right) >= 0) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }

        return node;
    }

    public void insertNode(int key) {
        root = insertNode(key, root);
    }

    private Node insertNode(int key, Node node) {
        // No node at current position --> store new node at current position
        if (node == null) {
            node = new Node(key);
        }

        // Otherwise, traverse the tree to the left or right depending on the key
        else if (key < node.data) {
            node.left = insertNode(key, node.left);
        } else if (key > node.data) {
            node.right = insertNode(key, node.right);
        } else {
            throw new IllegalStateException("BST already contains a node with key " + key);
        }

        updateHeight(node);

        node = rebalance(node);
        return node;
    }

    public void deleteNode(int key) {
        root = deleteNode(key, root);
    }

    private Node deleteNode(int key, Node node) {

        if (node == null) {
            return null;
        }

        if (key < node.data) {
            node.left = deleteNode(key, node.left);
        } else if (key > node.data) {
            node.right = deleteNode(key, node.right);
        } else {
            if(node.left == null || node.right == null) {
                Node child = node.left != null ? node.left : node.right;
                if(child == null) {
                    return null;
                } else {
                    node = child;
                }
            } else {
                Node temp = node.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                node.right = deleteNode(temp.data, node.right);
                node.data = temp.data;
            }
        }
        updateHeight(node);
        node = rebalance(node);
        return node;
    }

}
