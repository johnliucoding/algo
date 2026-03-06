package coding.pattern.chap3;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class LRUCacheTest {

    private static final Logger log = LoggerFactory.getLogger(LRUCacheTest.class);

    @Test
    void testLRUCache() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 100);
        log.atInfo().setMessage("cache is {}").addArgument(cache).log();
        cache.put(2, 250);
        log.atInfo().setMessage("cache is {}").addArgument(cache).log();
        int val = cache.get(2);
        log.atInfo().setMessage("return {}").addArgument(val).log();
        cache.put(4, 300);
        log.atInfo().setMessage("cache is {}").addArgument(cache).log();
        cache.put(3, 200);
        log.atInfo().setMessage("cache is {}").addArgument(cache).log();
        int val2 = cache.get(4);
        log.atInfo().setMessage("return {}").addArgument(val2).log();
        int val3 = cache.get(1);
        log.atInfo().setMessage("return {}").addArgument(val3).log();
    }
}