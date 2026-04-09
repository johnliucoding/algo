package goodrich.ch13;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liu Xiaodong
 * @since 2025/1/24 10:26 PM
 */
public class Trie {

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isEnd = false;
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (root.children.get(c) == null) {
                root.children.put(c, new Node());
            }
            cur = cur.children.get(c);
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isEnd;
    }
    public boolean startWith(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return true;
    }
}
