package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/22-22:34
 */
public class Problem_1799_MaximizeScoreAfterNOperations {

    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] dp = new int[1 << n];
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = gcd(nums[i], nums[j]);
            }
        }

        int status = 1 << n;
        for (int s = 1; s < status; s++) {
            int t = Integer.bitCount(s);
            if ((t & 1) != 0)
                continue;

            for (int i = 0; i < n; i++) {
                if (((s >> i) & 1) != 0) {
                    for (int j = i + 1; j < n; j++) {
                        if (((s >> j) & 1) != 0) {
                            dp[s] = Math.max(dp[s], dp[s ^ (1 << i) ^ (1 << j)] + t / 2 * gcd[i][j]);
                        }
                    }
                }
            }
        }

        return dp[status - 1];
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
