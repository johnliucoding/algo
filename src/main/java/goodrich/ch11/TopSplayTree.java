package goodrich.ch11;

// void insert(x) Insert x
// void remove(x) Remove x
// Comparable find(x) return item matches x
// boolean isEmpty() return true if empty
// void clear() remove all items
public class TopSplayTree<E extends Comparable<E>> {


    static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;
    }


    public TopSplayTree() {
    }
}
