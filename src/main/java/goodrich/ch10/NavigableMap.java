package goodrich.ch10;

/**
 * @author Liu Xiaodong
 * @since 2024/9/10 10:43 AM
 */
public interface NavigableMap<K,V> extends Map<K,V> {
    // returns the entry with the least key value greater than
    // or equal to k(or null, if no such entry exists)
    Entry<K,V> ceilingEntry(K key);
    // returns the entry with the greatest key value less than
    // or equal to k(or null, if no such entry exists)
    Entry<K,V> floorEntry(K key);

    // returns the entry with the least key value strictly greater
    // than k (or null if no such entry exists)
    Entry<K,V> higherEntry(K key);
    // returns the entry with the greatest key value strictly less
    // than k (or null if no such entry exists)
    Entry<K,V> lowerEntry(K key);

    // returns the entry with smallest key value(or null,
    // if the map is empty)
    Entry<K,V> firstEntry();
    // returns the entry with largest key value(or null,
    // is the map is empty)
    Entry<K,V> lastEntry();

    // returns an iteration of all entries with key greater than or
    // equal to fromKey, but strictly less than toKey
    Entry<K,V> subMap(K fromKey, K toKey);
}
