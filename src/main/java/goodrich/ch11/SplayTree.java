package goodrich.ch11;

import java.util.ArrayList;

/**
 * @author Liu Xiaodong
 * @since 2024/10/8 11:06 AM
 */
public class SplayTree<T extends Comparable<T>> {

    static class SplayNode<T extends Comparable<T>> {
        T data;
        SplayNode<T> left;
        SplayNode<T> right;

        public SplayNode(T data) {
            this.data = data;
        }
    }

    SplayNode<T> root = null;

    public boolean insert(T data) {
        if (root == null) {
            root = new SplayNode<>(data);
            return true;
        }
        root = splayUtil(root, data);
        if(data.compareTo(root.data) ==0) {
            return false;
        }
        final ArrayList<SplayNode<T>> l_r = split(data);
        final SplayNode<T> left = l_r.get(0);
        final SplayNode<T> right = l_r.get(1);

        root = new SplayNode<>(data);
        root.left = left;
        root.right = right;
        return true;
    }


    public boolean remove(T data) {
        if (root == null) {
            return false;
        }
        root = splayUtil(root, data);
        if(data.compareTo(root.data) !=0) {
            return false;
        }

        final SplayNode<T> left = root.left;
        final SplayNode<T> right = root.right;

        root.left = null;
        root.right = null;

        root = join(left, right);
        return true;
    }


    public boolean contains(T data) {
        if (root == null) {
            return false;
        }
        this.root = splayUtil(root, data);
        return this.root.data.compareTo(data) == 0;
    }

    /** * To FindMax Of Tree with specified root * */
    private T findMax(SplayNode<T> root) {
        SplayNode<T> temp = root;
        while (temp.right != null) temp = temp.right;
        return temp.data;
    }

    /** * To FindMin Of Tree with specified root * */
    private T findMin(SplayNode<T> root) {
        SplayNode<T> temp = root;
        while (temp.left != null) temp = temp.left;
        return temp.data;
    }


    // left must not be null
    private SplayNode<T> rightRotate(SplayNode<T> node) {
        final SplayNode<T> child = node.left;
        node.left = child.right;
        child.right = node;
        return child;
    }
    // right must not be null
    private SplayNode<T> leftRotate(SplayNode<T> node) {
        final SplayNode<T> child = node.right;
        node.right= child.left;
        child.left = node;
        return child;
    }

    private SplayNode<T> splayUtil(SplayNode<T> node, T data) {
        if (node == null) {
            return node;
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            if (node.left == null) {
                return node;
            }

            if (data.compareTo(node.left.data) < 0) {
                node.left.left = splayUtil(node.left.left, data);
                // rr zig-zig
                node = rightRotate(node);
                return node.left == null ? node : rightRotate(node);
            } else if (data.compareTo(node.left.data) > 0) {
                node.left.right = splayUtil(node.left.right, data);
                // lr zig-zag
                if(node.left.right != null) {
                    node.left = leftRotate(node.left);
                }
                return rightRotate(node);
            } else {
                // zig
                return rightRotate(node);
            }
        } else if (cmp > 0) {
            if(node.right == null) {
                return null;
            }
            if(data.compareTo(node.right.data) > 0) {
                node.right.right = splayUtil(node.right.right, data);
                node = leftRotate(node);
                return node.right == null ? node : leftRotate(node);
            } else if (data.compareTo(node.right.data) < 0) {
                node.right.left = splayUtil(node.right.left, data);
                if(node.right.left != null) {
                    node.right = rightRotate(node.right);
                }
                return leftRotate(node);
            } else {
                // zig
                return leftRotate(node);
            }

        } else {
            return node;
        }
    }

    private ArrayList<SplayNode<T>> split(T data) {
        SplayNode<T> left;
        SplayNode<T> right;

        if(data.compareTo(root.data) > 0) {
            right = root.right;
            left = root;
            left.right = null;
        } else {
            left = root.left;
            right = root;
            right.left = null;
        }
        final ArrayList<SplayNode<T>> l_r = new ArrayList<>();
        l_r.add(left);
        l_r.add(right);
        return l_r;
    }

    private SplayNode<T> join(SplayNode<T> l, SplayNode<T> r) {
        if(l == null) {
            return r;
        }
        final SplayNode<T> newRoot = splayUtil(l, findMax(l));
        newRoot.right = r;
        return newRoot;
    }
}


