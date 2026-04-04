package coding.pattern.chap17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KthLargestElementHeapTest {

    @Test
    void kthLargest() {
        int[] arr = {5,2,4,3,1,6};
        int k = 3;
        int result = KthLargestElementHeap.kthLargest(arr, k);
        assertEquals(4, result);
    }
}