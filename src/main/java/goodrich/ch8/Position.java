package goodrich.ch8;

/**
 * @author Liu Xiaodong
 * @since 2024/7/23 10:35 PM
 */
public interface Position<E> {
    /**
     * Returns the element stored at this position
     *
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}
