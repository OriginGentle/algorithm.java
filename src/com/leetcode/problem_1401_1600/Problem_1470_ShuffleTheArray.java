package com.leetcode.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/8/29-08:29
 */
public class Problem_1470_ShuffleTheArray {

    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n << 1];
        for (int i = 0; i < n; i++) {
            ans[i << 1] = nums[i];
            ans[i << 1 | 1] = nums[i + n];
        }
        return ans;
    }
}
