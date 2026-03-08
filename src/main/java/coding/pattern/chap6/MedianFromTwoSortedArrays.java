package coding.pattern.chap6;

public class MedianFromTwoSortedArrays {

    public static double median(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int total_len = nums1.length + nums2.length;

        int left = 0;
        int right = nums1.length - 1;

        while (true) {
            int l1_index = left + (right - left) / 2;
            int l2_index = total_len / 2 - (l1_index + 1) - 1;
            // set to -infinity or +infinity if out of bounds
            double l1 = l1_index < 0 ? Double.NEGATIVE_INFINITY : nums1[l1_index];
            double r1 = l1_index >= nums1.length -1 ? Double.POSITIVE_INFINITY : nums1[l1_index + 1];
            double l2 = l2_index < 0 ? Double.NEGATIVE_INFINITY : nums2[l2_index];
            double r2 = l2_index >= nums2.length -1 ? Double.POSITIVE_INFINITY : nums2[l2_index + 1];

            // if l1 > r2, then l1 is too far to the right, narrow the search space toward the left
            if(l1 > r2) {
                right = l1_index - 1;
            // if l2 > r1, the l1 is too far to the left, narrow the search space toward the right
            } else if (l2 > r1) {
                left = l1_index + 1;
            // if both l1 and l2 are less than or equal to both r1 and r2, we found the correct slice
            } else {
                if(total_len % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.min(r1, r2);
                }
            }
        }
    }
}
