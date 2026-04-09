package goodrich.ch8;

import java.util.ArrayList;

/**
 * @author Liu Xiaodong
 * @since 2024/7/24 10:13 PM
 */
public interface BinaryTree<E> extends Tree<E> {
    /**
     * Returns the Position of p's left child(or null if no child exists)
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position of p's right child(or null if no child exists)
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position of p's sibling(or null if no sibling exists)
     */
    default Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        final Position<E> parent = parent(p);
        if (parent == null) {
            return null;
        }
        if (p == left(parent)) {
            return right(parent);
        } else {
            return left(parent);
        }
    }

    default int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    default Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        final ArrayList<Position<E>> snapshot = new ArrayList<>(numChildren(p));
        if (left(p) != null) {
            snapshot.add(left(p));
        }
        if (right(p) != null) {
            snapshot.add(right(p));
        }
        return snapshot;
    }

}
