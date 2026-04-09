package goodrich.ch10;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

/**
 * @author Liu Xiaodong
 * @since 2024/9/13 2:07 PM
 */
public class SkipListMap<K, V> {

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] next;

        @SuppressWarnings("unchecked")
        public Node(K key, V value, int h) {
            this.key = key;
            this.value = value;
            this.next = (Node<K, V>[]) new Node[h + 1];
        }

        @Override
        public String toString() {
            return value + ":" + key;
        }
    }

    private final Comparator<K> cmp;
    private final Node<K, V> head;
    private int level;
    private int size;
    private final Random rand;
    private final int maxLevel;

    public SkipListMap(Comparator<K> cmp) {
        this.cmp = cmp;
        this.maxLevel = 32;
        this.head = new Node<>(null, null, this.maxLevel);
        this.level = 0;
        this.size = 0;
        this.rand = new Random();
    }

    private boolean less(K k1, K k2) {
        return cmp.compare(k1, k2) < 0;
    }

    private boolean equal(K k1, K k2) {
        return cmp.compare(k1, k2) == 0;
    }

    private int pickHeight() {
        int z = rand.nextInt();
        int k = 0;
        int m = 1;
        while ((z & m) != 0 && k < maxLevel) {
            k++;
            m <<= 1;
        }
        return k;
    }

    private V find(K key) {
        Node<K, V> current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && less(current.next[i].key, key)) {
                current = current.next[i];
            }
        }
        final Node<K, V> node = current.next[0];
        if (node != null && equal(node.key, key)) {
            return node.value;
        } else {
            return null;
        }
    }


    public V remove(K key) {

        @SuppressWarnings("unchecked")
        Node<K, V>[] stack = (Node<K, V>[]) new Node[maxLevel + 1];

        Node<K, V> current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && less(current.next[i].key, key)) {
                current = current.next[i];
            }
            stack[i] = current;
        }

        Node<K, V> node = current.next[0];
        if (node != null && equal(node.key, key)) {
            for (int i = 0; i <= level; i++) {
                if (stack[i].next[i] != node) {
                    break;
                }
                stack[i].next[i] = node.next[i];
            }
            // 修改level
            while (level > 0 && head.next[level] == null) {
                level--;
            }
            // 修改 size
            size--;
            return node.value;
        }
        return null;

    }


    public int size() {
        return size;
    }

    public V add(K key, V value) {
        @SuppressWarnings("unchecked")
        Node<K, V>[] stack = (Node<K, V>[]) new Node[maxLevel + 1];

        Node<K, V> current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null &&
                    less(current.next[i].key, key)) {
                current = current.next[i];
            }
            stack[i] = current;
        }

        // 已经有相同的key
        final Node<K, V> node = current.next[0];
        if (node != null && equal(node.key, key)) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        final int newHeight = pickHeight();
        if (newHeight > level) {
            for (int i = level + 1; i <= newHeight; i++) {
                stack[i] = head;
            }
            // 修改 level
            level = newHeight;
        }

        final Node<K, V> newNode = new Node<>(key, value, newHeight);
        for (int i = 0; i <= newHeight; i++) {
            newNode.next[i] = stack[i].next[i];
            stack[i].next[i] = newNode;
        }
        // 修改 size
        size++;
        return newNode.value;
    }


    @Override
    public String toString() {
        final StringBuilder stb = new StringBuilder();
        for (int i = level; i >= 0; i--) {
            stb.append(String.format("%-15s", "head->"));

            Node<K, V> currentLevel = head.next[i];
            Node<K, V> lastLevel = head.next[0];
            while (lastLevel != null && currentLevel != null) {
                if (Objects.equals(currentLevel.key, lastLevel.key)) {
                    stb.append(String.format("%-8s", lastLevel + "->"));
                    currentLevel = currentLevel.next[i];
                    lastLevel = lastLevel.next[0];

                } else {
                    stb.append(String.format("%-8s", ""));
                    lastLevel = lastLevel.next[0];
                }
            }

            stb.append("\n");
        }

        return stb.toString();
    }


    public static void main(String[] args) {

        final SkipListMap<Integer, Character> skip = new SkipListMap<>(Integer::compareTo);
        // 添加后
        for (int i = 0; i < 10; i++) {
            skip.add(i, (char) ('a' + i));
        }
        System.out.println(skip);


        // 删除
        skip.remove(4);
        skip.remove(8);
        System.out.println(skip);

        // 替换
        skip.add(5, 'z');
        skip.add(4, 'o');
        System.out.println(skip);

        System.out.println(skip.find(10));
        System.out.println(skip.find(4));
    }
}
