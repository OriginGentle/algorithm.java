package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @since 2021/11/12-10:34
 */
public class Problem_0375_GuessNumberHigherOrLowerII {

    public static int[][] dp = new int[201][201];

    public static int getMoneyAmount(int n) {
        return process(1, n);
    }

    // 在l~r范围内猜数，返回猜对数字，至少花费多少钱
    public static int process(int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (dp[l][r] != 0) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        // 尝试l...r范围上所有的数
        for (int i = l; i <= r; i++) {
            int cur = Math.max(process(l, i - 1), process(i + 1, r)) + i;
            ans = Math.min(cur, ans);
        }
        dp[l][r] = ans;
        return ans;
    }
}
