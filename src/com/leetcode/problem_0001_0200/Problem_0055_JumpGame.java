package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/9-14:41
 */
public class Problem_0055_JumpGame {

    public static boolean canJump1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        return process(nums, 0);
    }

    // 从index位置出发，能否跳到nums的最后位置
    public static boolean process(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return true;
        }
        if (nums[index] == 0) {
            return false;
        }
        // 当前你在index位置，
        // 你最多可以跳 nums[index]步
        boolean ans = false;
        for (int i = 1; i <= nums[index]; i++) {
            if (process(nums, index + i)) {
                ans = true;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static boolean canJump2(int[] nums) {
        int n = nums.length;
        int mostRight = 0;
        for (int i = 0; i < n; i++) {
            if (i <= mostRight) {
                mostRight = Math.max(mostRight, i + nums[i]);
                if (mostRight >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
