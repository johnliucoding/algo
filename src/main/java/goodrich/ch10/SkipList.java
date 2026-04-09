package ch10;

import java.util.*;

/**
 * @author Liu Xiaodong
 * @since 2024/9/10 2:21 PM
 */
public class SkipList<K, V> {

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> right;
        Node<K, V> down;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "/" + value;
        }
    }

    private static final int MAX_LEVEL = 32;
    Node<K, V> headNode;
    int level;
    Random random;
    Comparator<K> comparator;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        random = new Random();
        headNode = new Node<>(null, null);
        level = 0;
    }

    private boolean less(K k1, K k2) {
        return comparator.compare(k1, k2) < 0;
    }

    // 这个不是找到了最底层
    public Node<K, V> search(K key) {
        Node<K, V> current = headNode;
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current;
            } else if (current.right == null) {
                current = current.down;
            } else if (less(key, current.right.key)) {
                current = current.down;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node<K, V> current = headNode;
        while (current != null) {
            if (current.right == null) {
                current = current.down;
            } else if (Objects.equals(current.right.key, key)) {
                current.right = current.right.right;
                current = current.down;
            } else if (less(key, current.right.key)) {
                current = current.down;
            } else {
                current = current.right;
            }
        }
    }

    public void insert(K key, V value) {
        Node<K, V> search = search(key);
        if (search != null) {
            while (search != null) {
                search.value = value;
                search = search.down;
            }
            return;
        }
        final Deque<Node<K, V>> stack = new ArrayDeque<>();
        Node<K, V> current = headNode;
        while (current != null) {
            if (current.right == null) {
                stack.push(current);
                current = current.down;
            } else if (less(key, current.right.key)) {
                stack.push(current);
                current = current.down;
            } else {
                current = current.right;
            }
        }
        int level = 0;
        Node<K, V> downNode = null;
        while (!stack.isEmpty()) {
            current = stack.pop();
            final Node<K, V> kvNode = new Node<>(key, value);
            kvNode.down = downNode;
            downNode = kvNode;
            if (current.right == null) {
                current.right = kvNode;
            } else {
                kvNode.right = current.right;
                current.right = kvNode;
            }
            if (level > MAX_LEVEL) {
                break;
            }
            final double choice = random.nextDouble();
            if (choice < 0.5) {
                break;
            }
            level++;
            if (level > this.level) {
                this.level = level;
                final Node<K, V> newHead = new Node<>(null, null);
                newHead.down = headNode;
                headNode = newHead;
                stack.push(headNode);
            }
        }
    }

    @Override
    public String toString() {
        Node<K, V> current = headNode;

        Node<K, V> lastNode = current;
        while (lastNode.down != null) {
            lastNode = lastNode.down;
        }
        final StringBuilder buf = new StringBuilder();
        while (current != null) {
            buf.append(String.format("%-15s", current + "->"));
            Node<K, V> right = current.right;
            Node<K, V> rightLast = lastNode.right;
            while (rightLast != null && right != null) {
                if (Objects.equals(right.key, rightLast.key)) {
                    buf.append(String.format("%-8s", rightLast + "->"));
                    rightLast = rightLast.right;
                    right = right.right;
                } else {
                    buf.append(String.format("%-8s", ""));
                    rightLast = rightLast.right;
                }
            }
            current = current.down;
            buf.append("\n");
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        final SkipList<Integer, Character> skip = new SkipList<>(Integer::compareTo);

        // 添加后
        for (int i = 0; i < 10; i++) {
            skip.insert(i, (char) ('a' + i));
        }
        System.out.println(skip);


        // 删除
        skip.delete(4);
        skip.delete(8);
        System.out.println(skip);

        // 替换
        skip.insert(5, 'z');
        skip.insert(4, 'o');
        System.out.println(skip);

        System.out.println(skip.search(10));
        System.out.println(skip.search(4));
    }
}
