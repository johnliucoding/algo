package sort;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class SortTest {

    private static final Logger log = LoggerFactory.getLogger(SortTest.class);

    @Test
    void insertionSort() {
        sortTestHelper(InsertionSort::insertionSort);
    }

    @Test
    void selectionSort() {
        sortTestHelper(SelectionSort::selectionSort);
    }

    private void sortTestHelper(Consumer<Character[]> testMethod) {
        var data = new Character[] {'r', 'a', 'c', 'b'};
        log.atInfo().setMessage("input: {}").addArgument(data).log();
        testMethod.accept(data);
        log.atInfo().setMessage("sorted: {}").addArgument(data).log();
        assertArrayEquals(new Character[] {'a', 'b', 'c','r'}, data);
    }
}