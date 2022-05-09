package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @since 2021/11/6-17:32
 */
public class Problem_0268_MissingNumber {

    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int total = (n * (n + 1)) >> 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return total - sum;
    }
}
