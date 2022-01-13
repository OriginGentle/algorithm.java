package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/13-8:42
 */
public class Problem_0747_LargestNumberAtLeastTwiceOfOthers {

    public static int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int x = -1, y = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[y]) {
                x = y;
                y = i;
            } else if (x == -1 || nums[i] > nums[x]) {
                x = i;
            }
        }
        return nums[y] >= (nums[x] << 1) ? y : -1;
    }
}
