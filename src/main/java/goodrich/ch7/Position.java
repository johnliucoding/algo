package ch7;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 7:07 PM
 */
public interface Position<E> {
    /**
     * Returns the element stored at this position
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}
