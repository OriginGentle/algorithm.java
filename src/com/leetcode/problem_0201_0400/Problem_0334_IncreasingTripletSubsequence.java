package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @since 2022/1/12-11:50
 */
public class Problem_0334_IncreasingTripletSubsequence {

    public static boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] left = new int[n];
        left[0] = nums[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i - 1], nums[i]);
        }
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > left[i - 1] && nums[i] < right[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean increasingTriplet2(int[] nums) {
        int small = Integer.MAX_VALUE, middle = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= middle) {
                middle = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
