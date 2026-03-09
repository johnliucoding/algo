package coding.pattern.chap6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class WeightedRandomSelectionTest {

    private static final Logger log = LoggerFactory.getLogger(WeightedRandomSelectionTest.class);

    @Test
    void select() {

        int[] weights = {3, 1, 2, 4};
        WeightedRandomSelection selection = new WeightedRandomSelection(weights);
        int selected = selection.select();
        log.atInfo().setMessage("result index: {}").addArgument(selected).log();
    }
}