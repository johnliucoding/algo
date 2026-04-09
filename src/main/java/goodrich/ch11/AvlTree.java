package goodrich.ch11;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author Liu Xiaodong
 * @since 2024/9/28 10:31 PM
 */
public class AvlTree<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root = null;
    private int size = 0;

    public int height() {
        if (root == null) {
            return -1;
        }
        return root.height;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T data) {
        return contains(root, data);
    }

    private boolean contains(Node<T> node, T data) {
        if (node == null) {
            return false;
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, data);
        } else if (cmp > 0) {
            return contains(node.right, data);
        } else {
            return true;
        }
    }

    public boolean insert(T data) {
        if (data == null) {
            return false;
        }
        if (!contains(root, data)) {
            root = insert(root, data);
            size++;
            return true;
        }
        return false;
    }

    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } else if (cmp > 0) {
            node.right = insert(node.right, data);
        }
        updateHeight(node);

        return reBalance(node);
    }

    private void updateHeight(Node<T> node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;

        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    private int balanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;
        return leftHeight - rightHeight;
    }

    private Node<T> reBalance(Node<T> node) {
        if (balanceFactor(node) == 2) {
            // left heavy
            if (balanceFactor(node.left) >= 0) {
                return leftLeftCase(node);
            } else {
                return leftRightCase(node);
            }
        } else if (balanceFactor(node) == -2) {
            // right heavy
            if (balanceFactor(node.right) <= 0) {
                return rightRightCase(node);
            } else {
                return rightLeftCase(node);
            }
        }
        return node;
    }

    private Node<T> leftLeftCase(Node<T> node) {
        return rightRotation(node);
    }

    private Node<T> leftRightCase(Node<T> node) {
        node.left = leftRotation(node.left);
        return leftLeftCase(node);
    }

    private Node<T> rightRightCase(Node<T> node) {
        return leftRotation(node);
    }

    private Node<T> rightLeftCase(Node<T> node) {
        node.right = rightRotation(node.right);
        return rightRightCase(node);
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public boolean remove(T data) {
        if (data == null) {
            return false;
        }
        if (contains(root, data)) {
            root = remove(root, data);
            size--;
            return true;
        }
        return false;
    }

    private Node<T> remove(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, data);
        } else if (cmp > 0) {
            node.right = remove(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                final Node<T> child = node.left != null ? node.left : node.right;
                if (child == null) {
                    return null;
                } else {
                    node = child;
                }
            } else {
                if (node.left.height > node.right.height) {
                    final Node<T> maxNode = findMax(node.left);
                    node.data = maxNode.data;
                    node.left = remove(node.left, maxNode.data);
                } else {
                    final Node<T> minNode = findMin(node.right);
                    node.data = minNode.data;
                    node.right = remove(node.right, minNode.data);
                }
            }
        }
        updateHeight(node);
        return reBalance(node);
    }

    private Node<T> findMax(Node<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Iterator<T> iterator() {
        final int expectedNodeCount = size;
        final ArrayDeque<Node<T>> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }

        return new Iterator<T>() {
            Node<T> trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != size) {
                    throw new ConcurrentModificationException();
                }
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != size) {
                    throw new ConcurrentModificationException();
                }
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }
                final Node<T> node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }
                return node.data;
            }
        };
    }

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;
        int height;

        public Node(T data) {
            this.data = data;
        }
    }
}
