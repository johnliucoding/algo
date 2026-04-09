package goodrich.ch13;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Liu Xiaodong
 * @since 2025/1/26 9:58 PM
 */
public class Huffman {

    @Getter
    @RequiredArgsConstructor
    static class Node implements Comparable<Node> {
        private final int frequency;
        private Node left;
        private Node right;

        public Node(Node leftNode, Node rightNode) {
            this.frequency = leftNode.frequency + rightNode.frequency;
            this.left = leftNode;
            this.right = rightNode;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(frequency, node.frequency);
        }
    }

    @Getter
    @EqualsAndHashCode(callSuper = true)
    static class Leaf extends Node {
        private final char character;

        public Leaf(char character, int frequency) {
            super(frequency);
            this.character = character;
        }
    }

    private Node root;
    private final String text;
    private final Map<Character, String> huffmanCodes;
    private Map<Character, Integer> charFrequencies;

    public Huffman(String text) {
        this.text = text;
        fillCharFrequenciesMap();
        huffmanCodes = new HashMap<>();
    }

    private void fillCharFrequenciesMap() {
        charFrequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            charFrequencies.compute(c, (k, i) -> i != null ? i + 1 : 1);
        }
    }

    public String encode() {
        final PriorityQueue<Node> queue = new PriorityQueue<>();
        charFrequencies.forEach((c, f) -> {
            queue.add(new Leaf(c, f));
        });
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll()));
        }
        generateHuffmanCodes(root = queue.poll(), "");
        return getEncodedText();
    }

    private void generateHuffmanCodes(Node node, String code) {
        if (node instanceof Leaf leaf) {
            huffmanCodes.put(leaf.getCharacter(), code);
            return;
        }
        generateHuffmanCodes(node.getLeft(), code.concat("0"));
        generateHuffmanCodes(node.getRight(), code.concat("1"));
    }

    private String getEncodedText() {
        final StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(huffmanCodes.get(c));
        }
        return sb.toString();
    }

    public String decode(String encodedText) {
        final StringBuilder sb = new StringBuilder();
        Node cur = root;
        for (char c : encodedText.toCharArray()) {
            cur = c == '0' ? cur.getLeft() : cur.getRight();
            if(cur instanceof Leaf l) {
                sb.append(l.getCharacter());
                cur = root;
            }
        }
        return sb.toString();
    }

    public void printCodes() {
        huffmanCodes.forEach((c, code) -> {
            System.out.println(c + ": " + code);
        });
    }


    public static void main(String[] args) {
        Huffman huffman = new Huffman("aaaaaaaabbbbbbbccccdd");

        final String encoded = huffman.encode();
        System.out.println(encoded);

        huffman.printCodes();

        final String decoded = huffman.decode(encoded);
        System.out.println(decoded);
    }

}
