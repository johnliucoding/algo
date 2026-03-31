package coding.pattern.chap14;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class KeypadCombinationTest {

    private static final Logger log = LoggerFactory.getLogger(KeypadCombinationTest.class);

    @Test
    void combinations() {
        List<String> combinations = KeypadCombination.combinations("69");
        log.atInfo().setMessage("output: {}").addArgument(combinations).log();
    }
}