package com.leetcode.questions.problem_1001_1200;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2021/12/3-13:21
 */
public class Problem_1005_MaximizeSumOfArrayAfterKNegations {

    // 贪心
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        Arrays.sort(nums);
        return sum - (k % 2 == 0 ? 0 : 2 * nums[0]);
    }
}
