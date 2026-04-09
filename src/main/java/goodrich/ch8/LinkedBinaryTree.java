package goodrich.ch8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Liu Xiaodong
 * @since 2024/7/25 2:18 PM
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {

    private Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() {
    }

    private Node<E> creatNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(element, parent, left, right);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E> node)) {
            throw new IllegalArgumentException("Not valid position type");
        }
        // intentionally sets the parent field of a deleted node to refer to itself, in accordance with our conventional representation of a defunct node
        if (node.getParent() == node) {
            throw new IllegalArgumentException("p is no longer in the tree");
        }
        return node;
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return node.getLeft();
    }


    // core method

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        }
        root = creatNode(e, null, null, null);
        size++;
        return root;
    }


    // update method

    /**
     * Creates a new left child of Position p storing element e; return its Position
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalStateException, IllegalArgumentException {
        final Node<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalStateException("p already has a left node");
        }
        final Node<E> child = creatNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e; return its Position
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalStateException, IllegalArgumentException {
        final Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalStateException("p already has a right node");
        }
        final Node<E> child = creatNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with e and returns the replaced element
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        final E tmp = node.getElement();
        node.setElement(e);
        return tmp;
    }

    public void attach(Position<E> p,
                       LinkedBinaryTree<E> t1,
                       LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        if (isInternal(p)) {
            throw new IllegalArgumentException("p must be a leaf");
        }
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        }
        final Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
        if (child != null) {
            child.setParent(node.getParent());
        }
        if (node == root) {
            root = child;
        } else {
            final Node<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size--;
        final E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        //
        node.setParent(node);
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }


    // tree traversal method

    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }

    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> child : children(p)) {
            preorderSubtree(child, snapshot);
        }
    }

    public Iterable<Position<E>> preorder() {
        final List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {

        for (Position<E> child : children(p)) {
            postorderSubtree(child, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder() {
        final List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }
    }

    public Iterable<Position<E>> inorder() {
        final List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<Position<E>> breadthFirst() {
        final List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            final ArrayDeque<Position<E>> queue = new ArrayDeque<>();
            queue.offer(root());
            while (!queue.isEmpty()) {
                final Position<E> p = queue.poll();
                snapshot.add(p);
                for (Position<E> child : children(p)) {
                    queue.offer(child);
                }
            }
        }
        return snapshot;
    }

    @Data
    @AllArgsConstructor
    static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIter = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIter.hasNext();
        }

        @Override
        public E next() {
            return posIter.next().getElement();
        }

        @Override
        public void remove() {
            posIter.remove();
        }
    }
}
