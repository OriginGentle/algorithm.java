package com.leetcode.questions.problem_2801_3000;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2024/7/26-01:44
 */
public class Problem_2740_FindTheValueOfThePartition {

    public static int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            ans = Math.min(diff, ans);
        }
        return ans;
    }
}
