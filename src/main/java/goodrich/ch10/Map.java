package goodrich.ch10;

/**
 * @author Liu Xiaodong
 * @since 2024/9/3 9:06 AM
 */
public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

    int size();

    V get(K key);

    boolean containsKey(K key);

    V put(K key, V value);

    V remove(K key);

}
