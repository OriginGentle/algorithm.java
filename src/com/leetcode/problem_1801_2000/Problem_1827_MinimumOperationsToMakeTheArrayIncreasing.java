package com.leetcode.problem_1801_2000;

/**
 * @author ycb
 * @date 2022/12/11-01:11
 */
public class Problem_1827_MinimumOperationsToMakeTheArrayIncreasing {

    public int minOperations(int[] nums) {
        int op = 0, max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= max) {
                op += max + 1 - nums[i];
                ++max;
            } else {
                max = nums[i];
            }
        }
        return op;
    }
}
