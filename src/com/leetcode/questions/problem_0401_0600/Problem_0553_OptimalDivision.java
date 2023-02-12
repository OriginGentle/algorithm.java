package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @since 2022/2/27-17:51
 */
public class Problem_0553_OptimalDivision {

    public static String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return String.valueOf(nums[0]);
        }
        if (n == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder ans = new StringBuilder();
        ans.append(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < n; i++) {
            ans.append("/" + nums[i]);
        }
        ans.append(")");
        return ans.toString();
    }
}
