package com.training.day28;

/**
 * @author ycb
 * @since 2021/10/14-8:35
 */
public class Problem_0026_RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int done = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[++done] = nums[i];
            }
        }
        return done + 1;
    }
}
