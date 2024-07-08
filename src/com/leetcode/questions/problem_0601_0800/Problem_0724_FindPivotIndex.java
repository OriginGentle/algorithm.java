package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2024/7/8-23:44
 */
public class Problem_0724_FindPivotIndex {

    public static int pivotIndex(int[] nums) {
        int sum = 0, preSum = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (2 * preSum + nums[i] == sum) {
                return i;
            }

            preSum += nums[i];
        }

        return -1;
    }
}
