package ch8;

import java.util.Iterator;

/**
 * @author Liu Xiaodong
 * @since 2024/7/23 10:37 PM
 */
public interface Tree<E> extends Iterable<E> {
    /**
     * Returns the position of the root the tree
     * (or null if empty)
     *
     * @return position of the root
     */
    Position<E> root();

    /**
     * Returns the position of the parent of position p
     * (or null if p is root)
     *
     * @param p position
     * @return parent of p
     * @throws IllegalArgumentException if p is not a valid position
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns an iterable collection containing the children
     * of position p(if any)
     *
     * @param p position
     * @return iterable of children
     * @throws IllegalArgumentException if p is not a valid position
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the number of children of position p
     *
     * @param p position
     * @return number of children
     * @throws IllegalArgumentException if p is not a valid position
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns true if position p has at least one child
     *
     * @param p position
     * @return is internal
     * @throws IllegalArgumentException if p is not a valid position
     */
    default boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    /**
     * Returns true if position p does not have any children
     *
     * @param p position
     * @return is external
     * @throws IllegalArgumentException if p is not a valid position
     */
    default boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    /**
     * Returns true if position p is the root of the tree
     *
     * @param p position
     * @return is root
     * @throws IllegalArgumentException if p is not a valid position
     */
    default boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    /**
     * Returns number of elements
     *
     * @return number of elements
     */
    int size();

    /**
     * Returns true if the tree does not contain any elements
     *
     * @return is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns an iterator for all elements in the tree
     * (so that the tree itself if Iterable)
     *
     * @return an iterator of elements
     */
    Iterator<E> iterator();

    /**
     * Returns an iterable collection of all positions in the tree
     *
     * @return iterable of positions
     */
    Iterable<Position<E>> positions();

    /**
     * Returns the number of levels separating Position p from the root
     */
    default int depth(Position<E> p) throws IllegalArgumentException {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    /**
     * Returns the height of the subtree rooted at Position p
     */
    default int height(Position<E> p) throws IllegalArgumentException {
        int h = 0; // base case if p external
        for (Position<E> child : children(p)) {
            h = Math.max(h, 1 + height(child));
        }
        return h;
    }

}
