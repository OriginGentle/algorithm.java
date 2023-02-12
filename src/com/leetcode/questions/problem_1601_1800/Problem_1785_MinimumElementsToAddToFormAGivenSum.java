package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/16-00:22
 */
public class Problem_1785_MinimumElementsToAddToFormAGivenSum {

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }

        long diff = Math.abs(sum - goal);
        return (int) ((diff + limit - 1) / limit);
    }
}
