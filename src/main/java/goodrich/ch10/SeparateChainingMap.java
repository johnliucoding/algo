package ch10;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Liu Xiaodong
 * @since 2024/9/9 9:22 AM
 */
public class SeparateChainingMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>> {



    static class Entry<K, V>  implements Map.Entry<K, V> {
        K key;
        V value;
        int hash;
        Entry<K,V> next;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }


    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int threshold;

    private int size;


    Entry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public SeparateChainingMap(int capacity, double maxLoadFactor) {
        if (capacity <= 0) {throw new IllegalArgumentException("Illegal capacity: " + capacity);}
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)) {throw new IllegalArgumentException("Illegal maxLoadFactor: " + maxLoadFactor);}
        this.maxLoadFactor = maxLoadFactor;
        final int cap = Math.max(DEFAULT_CAPACITY, capacity);
        this.threshold = (int) (cap * maxLoadFactor);
        this.table = (Entry<K, V>[]) new Entry[cap];
    }

    public SeparateChainingMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }
    public SeparateChainingMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }


    @Override
    public int size() {
        return size;
    }

    /**
     * converts a hash value to an index.
     * @param keyHash
     * @return
     */
    private int indexFor(int keyHash, int length) {
        return (keyHash & 0x7FFFFFFF) % length;
    }

    @Override
    public V get(K key) {
        if(key == null) {
            throw new IllegalArgumentException("Null key");
        }
        Entry<K, V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    private Entry<K, V> getEntry(K key) {
        if(size == 0) {
            return null;
        }
        int hash = hash(key);
        for(Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            if(e.hash == hash && (e.key == key || e.key.equals(key))) {
                return e;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        final Entry<K, V> entry = getEntry(key);
        return entry != null;
    }

    @Override
    public V put(K key, V value) {
        if(key == null) throw new IllegalArgumentException("null key");
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        for(Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if(entry.hash == hash && (entry.key == key || entry.key.equals(key))) {
                final V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        addEntry(hash, key, value, index);
        return null;
    }

    private void addEntry(int hash, K key, V value, int index) {
        if((size >= threshold) && (table[index] != null)) {
            resize(2 * table.length);
            hash = hash(key);
            index = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, index);
    }

    private void createEntry(int hash, K key, V value, int index) {
        final Entry<K, V> e = table[index];
        table[index] = new Entry<>(key, value, hash, e);
        size++;
    }

    private void resize(int newCapacity) {
        Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * maxLoadFactor);
    }
    private void transfer(Entry<K, V>[] newTable) {
        Entry<K, V>[] src = table;
        final int newCapacity = newTable.length;
        for (int i = 0; i < src.length; i++) {
            Entry<K, V> e = src[i];
            if(e != null) {
                src[i] = null;
                do {
                    final Entry<K, V> next = e.next;
                    final int index = indexFor(e.hash, newCapacity);
                    e.next = newTable[index];
                    newTable[index] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    private int hash(K key) {
        return key.hashCode();
    }



    @Override
    public V remove(K key) {
        Entry<K, V> e = removeEntryForKey(key);
        return null == e ? null : e.value;
    }

    private Entry<K, V> removeEntryForKey(K key) {
        if(size == 0) {
            return null;
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        Entry<K, V> prev = table[i];
        Entry<K, V> e = prev;
        while(e != null) {
            final Entry<K, V> next = e.next;
            if(e.hash == hash && (e.key == key || e.key.equals(key))) {
                size--;
                if(prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                }
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry<K, V> next; // next entry to return
        int index;           // current slot
        Entry<K, V> current; // current entry

        HashIterator() {
            if(size > 0) { // advance to first entry
                Entry<K, V>[] t = table;
                for(int i = 0; i < t.length; i++) {
                    if((next = t[i]) != null) {
                        index = i;
                        break;
                    }
                }
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry<K, V> nextEntry() {
            Entry<K, V> e = next;
            if(e == null) {
                throw new NoSuchElementException();
            }
            if((next = e.next) == null) {
                Entry<K, V>[] t = table;
                for(int i = index+1; i < t.length; i++) {
                    if((next = t[i]) != null) {
                        index = i;
                        break;
                    }
                }
            }
            current = e;
            return e;
        }

        public void remove() {
            if(current == null) {
                throw new IllegalStateException();
            }
            final K key = current.key;
            current = null;
            SeparateChainingMap.this.removeEntryForKey(key);
        }
    }

    private final class EntryIterator extends HashIterator<Map.Entry<K, V>> {

        @Override
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new EntryIterator();
    }
}
