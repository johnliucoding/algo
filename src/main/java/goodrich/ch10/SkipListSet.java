package goodrich.ch10;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

/**
 * @author Liu Xiaodong
 * @since 2024/9/12 12:01 AM
 */


public class SkipListSet<T> {

    static class Node<T> {
        T value;
        Node<T>[] forward; // array to hold references to different levels

        @SuppressWarnings("unchecked")
        public Node(T value, int level) {
            this.value = value;

            this.forward = (Node<T>[]) new Node[level + 1]; // level + 1 because level is 0-based
        }

        @Override
        public String toString() {
            return Objects.toString(value);
        }
    }

    private final Node<T> head;
    private final int maxLevel;
    private int level;
    private final Random random;
    private final Comparator<T> cmp;

    public SkipListSet(Comparator<T> cmp) {
        maxLevel = 16; // maximum number of levels
        level = 0; // current level of SkipList
        head = new Node<>(null, maxLevel);
        random = new Random();
        this.cmp = cmp;
    }

    private boolean less(T i, T j) {
        return cmp.compare(i, j) < 0;
    }

    public void insert(T value) {
        @SuppressWarnings("unchecked")
        Node<T>[] toUpdate = (Node<T>[] ) new Node[maxLevel + 1];

        Node<T> current = this.head;
        for(int i = level; i >= 0; i--) {
            while(current.forward[i] != null && less(current.forward[i].value, value)) {
                current = current.forward[i];
            }
            toUpdate[i] = current;
        }
        current = current.forward[0];
        if(current == null || current.value != value) {
            int lvl = randomLevel();
            if(lvl > level) {
                for(int i = level+1; i <= lvl; i++) {
                    toUpdate[i] = head;
                }
                level = lvl;
            }
            final Node<T> newNode = new Node<>(value, lvl);
            for (int i = 0; i <= lvl; i++) {
                newNode.forward[i] = toUpdate[i].forward[i];
                toUpdate[i].forward[i] = newNode;
            }
        }
    }

    private int randomLevel() {
        int level = 0;
//        random.nextBoolean()
        while ((random.nextInt() % 2 == 0) && level < maxLevel) {
            level++;
        }
        return level;
    }

    public boolean search(T value) {
        Node<T> current = head;
        for(int i = level; i >= 0; i--) {
            while(current.forward[i] != null && less(current.forward[i].value, value)) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value == value;
    }

    public void delete(T value) {
        @SuppressWarnings("unchecked")
        Node<T>[] toUpdate = (Node<T>[]) new Node[maxLevel + 1];

        Node<T> current = head;
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && less(current.forward[i].value, value)) {
                current = current.forward[i];
            }
            toUpdate[i] = current;
        }
        current = current.forward[0];

        if (current != null && current.value == value) {
            for (int i = 0; i <= level; i++) {
                if (toUpdate[i].forward[i] != current) break;
                toUpdate[i].forward[i] = current.forward[i];
            }

            while (level > 0 && head.forward[level] == null) {
                level--;
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder stb = new StringBuilder();
        for (int i = level; i >= 0; i--) {
            stb.append(String.format("%-15s", "head->"));

            Node<T> currentLevel = head.forward[i];
            Node<T> lastLevel = head.forward[0];
            while (lastLevel != null && currentLevel != null) {
                if (Objects.equals(currentLevel.value, lastLevel.value)) {
                    stb.append(String.format("%-8s", lastLevel + "->"));
                    currentLevel = currentLevel.forward[i];
                    lastLevel = lastLevel.forward[0];

                } else {
                    stb.append(String.format("%-8s", ""));
                    lastLevel = lastLevel.forward[0];
                }
            }

            stb.append("\n");
        }

        return stb.toString();
    }

    public static void main(String[] args) {
        final SkipListSet<Integer> skip = new SkipListSet<>(Integer::compareTo);
        // 添加后
        for (int i = 0; i < 10; i++) {
            skip.insert(i);
        }
        System.out.println(skip);


        // 删除
        skip.delete(4);
        skip.delete(8);
        System.out.println(skip);

        // 替换
        skip.insert(5);
        skip.insert(4);
        System.out.println(skip);

        System.out.println(skip.search(10));
        System.out.println(skip.search(4));
    }
}

