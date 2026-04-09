package goodrich.ch11;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author Liu Xiaodong
 * @since 2024/9/24 11:33 PM
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;

    private Node<T> root = null;

    public boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return nodeCount;
    }

    public boolean add(T elem) {
        if (contains(elem)) {
            return false;
        } else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    private Node<T> add(Node<T> node, T elem) {
        if (node == null) {
            node = new Node<>(null, null, elem);
        } else {
            if (elem.compareTo(node.data) < 0) {
                node.left = add(node.left, elem);
            } else {
                node.right = add(node.right, elem);
            }
        }
        return node;
    }

    public boolean remove(T elem) {
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node<T> remove(Node<T> node, T elem) {
        if (node == null) {
            return null;
        }
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node.right, elem);
        } else {
            if (node.left == null) {
                final Node<T> rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            } else if (node.right == null) {
                final Node<T> leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;
            } else {
                // find the leftmost node in the right subtree
                Node<T> tmp = findMin(node.right);
                node.data = tmp.data;
                node.right = remove(node.right, tmp.data);
            }
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        Node<T> cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    private Node<T> findMax(Node<T> node) {
        Node<T> cur = node;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    public T min() {
        final Node<T> minNode = findMin(root);
        if (minNode == null) {
            return null;
        }
        return minNode.data;
    }

    public T max() {
        final Node<T> maxNode = findMax(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.data;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node<T> node, T elem) {
        if (node == null) {
            return false;
        }
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, elem);
        } else if (cmp > 0) {
            return contains(node.right, elem);
        } else {
            return true;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public Iterator<T> traverse(TreeTraversalOrder order) {
        return switch (order) {
            case PRE_ORDER -> preOrderTraversal();
            case IN_ORDER -> inOrderTraversal();
            case POST_ORDER -> postOrderTraversal();
            case LEVEL_ORDER -> levelOrderTraversal();
        };
    }

    private Iterator<T> levelOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final ArrayDeque<Node<T>> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                final Node<T> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                return node.data;
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final ArrayDeque<Node<T>> stack1 = new ArrayDeque<>();
        final ArrayDeque<Node<T>> stack2 = new ArrayDeque<>();
        if (root != null) {
            stack1.push(root);
        }
        while (!stack1.isEmpty()) {
            final Node<T> node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return stack2.pop().data;
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final ArrayDeque<Node<T>> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }

        return new Iterator<T>() {
            Node<T> trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
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

    private Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final ArrayDeque<Node<T>> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }

        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return !stack.isEmpty();

            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                final Node<T> node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                return node.data;
            }
        };

    }

    public enum TreeTraversalOrder {
        PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
    }

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(Node<T> left, Node<T> right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

}
