package com.system.D_weekly.code_2023_05_3_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/5/20-13:37
 * @desc 假设每一次获得随机数的时候，这个数字大于100的概率是P
 * 尝试N次，其中大于100的次数在A次~B次之间的概率是多少?
 * 0 < P < 1, P是double类型
 * 1 <= A <= B <= N <= 100
 */
public class Code02_More100TimesBetweenAB {

    public static double probability(double p, int N, int A, int B) {
        double[][] dp = new double[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        double ans = 0;
        for (int i = A; i <= B; i++) {
            ans += process(p, 1D - p, N, i, dp);
        }
        return ans;
    }

    // 大于100的概率 more
    // 小于100的概率 less
    // 还可以可以仍 i 次
    // 仍出大于100的次数必须是 j次
    private static double process(double more, double less, int i, int j, double[][] dp) {
        if (j < 0 || i < j) {
            return 0;
        }

        // i >= 0 && j >= 0 && i >= j
        if (i == 0) {
            return 1D;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        double ans = more * process(more, less, i - 1, j - 1, dp) +
                less * process(more, less, i - 1, j, dp);
        dp[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        double P = 0.6;
        int N = 100;
        int A = 30;
        int B = 50;
        System.out.println(probability(P, N, A, B));
    }
}
