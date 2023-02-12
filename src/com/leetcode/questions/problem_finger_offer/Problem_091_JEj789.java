package com.leetcode.questions.problem_finger_offer;

/**
 * @author ycb
 * @date 2022/6/25-21:12
 */
public class Problem_091_JEj789 {

    public static int minCost(int[][] costs) {
        if (costs == null || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int a = costs[0][0], b = costs[0][1], c = costs[0][2];
        for (int i = 1; i < n; i++) {
            int d = Math.min(b, c) + costs[i][0];
            int e = Math.min(a, c) + costs[i][1];
            int f = Math.min(a, b) + costs[i][2];
            a = d; b = e; c = f;
        }
        return Math.min(a, Math.min(b, c));
    }
}
