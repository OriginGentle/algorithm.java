package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @since 2022/3/23-13:14
 */
public class Problem_1672_RichestCustomerWealth {

    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for (int[] acc : accounts) {
            int curSum = 0;
            for (int n : acc) {
                curSum += n;
            }
            max = Math.max(max, curSum);
        }
        return max;
    }
}
