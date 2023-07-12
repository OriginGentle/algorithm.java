package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @date 2023/7/12-10:11
 */
public class Problem_1911_MaximumAlternatingSubSequenceSum {

    public static long maxAlternatingSum(int[] nums) {
        long even = nums[0], odd = 0;
        for (int i = 1; i < nums.length; i++) {
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, even - nums[i]);
        }

        return even;
    }
}
