package coding.pattern.chap17;

import java.util.PriorityQueue;
// time O(n*log(k))
// space O(k)
public class KthLargestElementHeap {

    public static int kthLargest(int[] arr, int k) {

        var heap = new PriorityQueue<Integer>();
        for (int i : arr) {
            if(heap.size() < k){
                heap.offer(i);
            } else if(i > heap.peek()){
                heap.poll();
                heap.offer(i);
            }

        }
//        for (int i = 0; i < k+1; i++) {
//            heap.add(arr[i]);
//        }
//        for (int i = k+1; i < arr.length; i++) {
//            if(arr[i] > heap.peek()) {
//                heap.poll();
//                heap.add(arr[i]);
//            }
//        }
        return heap.peek();
    }
}
