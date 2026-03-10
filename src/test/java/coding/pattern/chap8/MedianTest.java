package coding.pattern.chap8;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MedianTest {

    private static final Logger log = LoggerFactory.getLogger(MedianTest.class);

    @Test
    void exercise() {
        Median median = new Median();
        median.add(3);
        median.add(6);
        log.atInfo().setMessage("get median: {}").addArgument(median::get_median).log();
        median.add(1);
        log.atInfo().setMessage("get median: {}").addArgument(median::get_median).log();
    }

}