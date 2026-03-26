package coding.pattern;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class EnumerateTest {

    @Test
    void over() {
    }

    @Test
    void testOver() {

        List<String> list = List.of("a", "b", "c");
        String[] arr = {"a", "b", "c"};

        Enumerate.over(arr, (str, idx) -> {
            String formatted = String.format("%d: %s", idx, str);
            IO.println(formatted);
        });
        Enumerate.over(list, (str, idx) -> {
            String formatted = String.format("%d: %s", idx, str);
            IO.println(formatted);
        });
    }
}