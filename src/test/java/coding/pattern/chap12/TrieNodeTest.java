package coding.pattern.chap12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieNodeTest {

    @Test
    void testTrie() {
        Trie trieNode = new Trie();
        trieNode.insert("top");
        trieNode.insert("bye");
        assertTrue(trieNode.hasPrefix("to"));
        assertFalse(trieNode.search("to"));
        trieNode.insert("to");
        assertTrue(trieNode.search("to"));
    }

    @Test
    void testWildcard() {
        Trie trieNode = new Trie();
        trieNode.insert("band");
        trieNode.insert("rat");
        assertTrue(trieNode.searchWithWildcard("ra."));
        assertFalse(trieNode.searchWithWildcard("b.."));
        trieNode.insert("ran");
        assertTrue(trieNode.searchWithWildcard(".an"));
    }

}