package coding.pattern.chap12;

public class Trie {

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public void insert(String word) {
        char[] charArr = word.toCharArray();
        TrieNode node = root;
        for (char c : charArr) {
            if (!node.children.containsKey(c)) {
                TrieNode newNode = new TrieNode();
                node.children.put(c, newNode);
            }
            node = node.children.get(c);
        }
        node.isWord = true;

    }
    public boolean search(String word) {
        char[] charArr = word.toCharArray();
        TrieNode node = root;
        for (char c : charArr) {
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return node.isWord;
    }
    public boolean hasPrefix(String prefix) {
        char[] charArr = prefix.toCharArray();
        TrieNode node = root;
        for (char c : charArr) {
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return true;
    }

    public boolean searchWithWildcard(String word) {
        char[] charArray = word.toCharArray();
        // start searching from the root of the trie
        return searchHelper(root, charArray, 0);
    }

    private boolean searchHelper(TrieNode node, char[] charArr, int start) {

        for (int i = start; i < charArr.length; i++) {
            // if a wildcard character is encountered, recursively search for
            // the rest of the word from each child node.
            if(charArr[i] == '.') {
                for (TrieNode child : node.children.values()) {
                    // if a match is found, return true
                    if(searchHelper(child, charArr, i + 1)) {
                        return true;
                    }
                }
                return false;
            } else if (node.children.containsKey(charArr[i])) {
                node = node.children.get(charArr[i]);
            } else  {
                return false;
            }
        }
        // after processing the last character, return true if we've reached the end of a word
        return node.isWord;
    }
}
