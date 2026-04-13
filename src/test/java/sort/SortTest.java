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
    void bubbleSort() {
        sortTestHelper(BubbleSort::bubbleSort);
    }

    @Test
    void insertionSort() {
        sortTestHelper(InsertionAndShellSort::insertionSort);
    }

    @Test
    void shellSort() {
        sortTestHelper(InsertionAndShellSort::shellSort);
    }


    @Test
    void selectionSort() {
        sortTestHelper(SelectionSort::selectionSort);
    }

    @Test
    void mergeSort() {
        sortTestHelper(MergeSort::sort);
    }

    @Test
    void quickSort() {
        sortTestHelper(Quicksort::sort);
    }


    private static <T extends Comparable<? super T>>  void sortTestHelper(Consumer<T[]> testMethod) {
        Character[] characters = new Character[] {'r', 'a', 'c', 'b'};
        Integer[] integers = {10, 55, -5, 34, 7, 22, 19};
        String[] strings = {"Void Elf", "Vulpera", "Human", "Trool", "Undead"};
//        log.atInfo().setMessage("input: a {}, i {}, str {}").addArgument(characters).addArgument(integers).addArgument(strings).log();

        testMethod.accept(cast(characters));
        testMethod.accept(cast(strings));
        testMethod.accept(cast(integers));

//        log.atInfo().setMessage("input: a {}, i {}, str {}").addArgument(characters).addArgument(integers).addArgument(strings).log();
        assertArrayEquals(new Character[] {'a', 'b', 'c','r'}, characters);
        assertArrayEquals(new Integer[] {-5, 7, 10, 19, 22, 34, 55}, integers);
        assertArrayEquals(new String[] {"Human", "Trool", "Undead", "Void Elf", "Vulpera"}, strings);

    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> T[] cast(Comparable<?>[] arr) {
        return (T[]) arr;
    }
}