package coding.pattern.chap16;

public class JumpToEnd {

    public static boolean canJumpToEnd(int[] nums) {
        // set the initial destination to the last index in the array

        int destination = nums.length - 1;
        // traverse the array in reverse to see if the destination can be reached by earlier indexes
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= destination) {
                // if we can reach the destination from the current index
                // set this index as the new destination
                destination = i;
            }
        }
        // if the destination is index 0, we can jump to the end from index 0
        return destination == 0;
    }
}
