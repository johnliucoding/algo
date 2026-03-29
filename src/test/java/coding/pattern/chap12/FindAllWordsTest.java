package coding.pattern.chap12;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindAllWordsTest {

    @Test
    void findAllWords() {
        char[][] board = {{'b', 'y', 's'}, {'r', 't', 'e'}, {'a', 'i', 'n'}};
        List<String> words = List.of("byte", "bytes", "rat", "rain", "trait", "train");
        List<String> result = FindAllWords.findAllWords(board, words);
        List<String> expect = Arrays.asList("byte", "bytes", "rain", "train");
        expect.sort(Comparator.naturalOrder());
        result.sort(Comparator.naturalOrder());
        assertEquals(expect, result);

    }
}