package goodrich.ch11.tree;

/**
 * @author Liu Xiaodong
 * @since 2024/10/9 2:15 PM
 */
/**
 * Nested static class for a binary tree node.
 */
public class Node<E> implements Position<E> {
    private E element;          // an element stored at this node
    private Node<E> parent;     // a reference to the parent node (if any)
    private Node<E> left;       // a reference to the left child (if any)
    private Node<E> right;      // a reference to the right child (if any)

    /**
     * Constructs a node with the given element and neighbors.
     *
     * @param e          the element to be stored
     * @param above      reference to a parent node
     * @param leftChild  reference to a left child node
     * @param rightChild reference to a right child node
     */
    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
        element = e;
        parent = above;
        left = leftChild;
        right = rightChild;
    }

    // accessor methods
    public E getElement() {
        return element;
    }

    // update methods
    public void setElement(E e) {
        element = e;
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parentNode) {
        parent = parentNode;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> leftChild) {
        left = leftChild;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> rightChild) {
        right = rightChild;
    }
} //----------- end of nested Node class -----------
