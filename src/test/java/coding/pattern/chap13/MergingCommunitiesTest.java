package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MergingCommunitiesTest {

    @Test
    void connect() {

        MergingCommunities mc = new MergingCommunities(5);
        mc.connect(0, 1);
        mc.connect(1, 2);
        assertEquals(1, mc.getCommunitySize(3));
        assertEquals(3, mc.getCommunitySize(0));
        mc.connect(3, 4);
        assertEquals(2, mc.getCommunitySize(4));
    }
}