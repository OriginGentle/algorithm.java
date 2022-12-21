package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/21-23:20
 */
public class Problem_1753_MaximumScoreFromRemovingStones {

    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c;
        int max = Math.max(Math.max(a, b), c);
        return Math.min(sum - max, sum / 2);
    }
}
