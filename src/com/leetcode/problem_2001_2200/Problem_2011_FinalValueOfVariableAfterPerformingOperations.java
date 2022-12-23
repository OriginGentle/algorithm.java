package com.leetcode.problem_2001_2200;

/**
 * @author ycb
 * @date 2022/12/23-22:27
 */
public class Problem_2011_FinalValueOfVariableAfterPerformingOperations {

    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String op : operations) {
            ans += op.equals("++X") || op.equals("X++") ? 1 : -1;
        }
        return ans;
    }
}
