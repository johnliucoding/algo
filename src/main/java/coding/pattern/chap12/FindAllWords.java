package coding.pattern.chap12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllWords {

    static class TrieNode {
        public Map<Character, TrieNode> children;
        public String word;

        public TrieNode() {
            children = new HashMap<>();
            word = null;
        }
    }

    public static List<String> findAllWords(char[][] board, List<String> words) {
        TrieNode root = new TrieNode();
        // insert every word into the trie
        for (String word : words) {
            char[] charArr = word.toCharArray();
            TrieNode node = root;
            for (char c : charArr) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.word = word;
        }

        List<String> result = new ArrayList<>();

        // start a DFS from each cell of the board that contains a child of the root
        // node, which represents the first letter of a word in the trie
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                char character = board[r][c];
                if(root.children.containsKey(character)) {
                    dfs(board, r, c, root.children.get(character), result);
                }
            }
        }



        return  result;

    }

    private static void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        // if the current node represents the end of a word, add the word to the result
        if(node.word != null) {
            result.add(node.word);
            // ensure the current word is only added once
            node.word = null;
        }
        char temp = board[r][c];
        // mark the current cell as visited
        board[r][c] = '#';
        // explore all adjacent cells that corresponding with a child of the current TrieNode
        record Direction(int i, int j) {}
        List<Direction> directions = List.of(new Direction(-1, 0),
                new Direction(1, 0),  new Direction(0, -1),
                new Direction(0, 1));
        for (Direction direction : directions) {
            int nextR = r + direction.i;
            int nextC = c + direction.j;
            if(withinBounds(nextR, nextC, board) && node.children.containsKey(board[nextR][nextC])) {
                dfs(board, nextR, nextC, node.children.get(board[nextR][nextC]), result);
            }
        }
        // backtrack by reverting the cell back to its original character
        board[r][c] = temp;
    }

    private static boolean withinBounds(int r, int c, char[][] board) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }
}
