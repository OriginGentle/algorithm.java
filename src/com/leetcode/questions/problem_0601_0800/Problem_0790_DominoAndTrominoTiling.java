package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/11/12-14:57
 */
public class Problem_0790_DominoAndTrominoTiling {

    public static final int MOD = (int) 1e9 + 7;

    public static int numTilings1(int n) {
        if (n == 1)
            return 1;

        long[] dp = new long[n + 1];
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] << 1 + dp[i - 3]) % MOD;
        }
        return (int) dp[n];
    }
}
