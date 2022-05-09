package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/4/22
 */
public class Problem_0396_RotateFunction {

    public static int maxRotateFunction(int[] nums) {
        int f = 0;
        int n = nums.length;
        int numSum = 0;
        for (int nu : nums) {
            numSum += nu;
        }
        for (int i = 0; i < n; i++) {
            f += nums[i] * i;
        }
        int res = f;
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(f, res);
        }
        return res;
    }
}
