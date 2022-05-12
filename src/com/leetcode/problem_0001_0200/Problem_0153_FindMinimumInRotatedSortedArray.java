package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/5/9-16:15
 */
public class Problem_0153_FindMinimumInRotatedSortedArray {

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2};
        int ans = findMin(nums);
        System.out.println(ans);
    }
}
