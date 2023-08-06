package com.system.D_weekly.code_2023_07_4_week;

/**
 * @author ycb
 * @date 2023/8/6-11:59
 * @desc 集团里有 n 名员工，他们可以完成各种各样的工作创造利润
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与
 * 如果成员参与了其中一项工作，就不能参与另一项工作
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划
 * 并且工作的成员总数最多为 n
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * 测试链接 : https://leetcode.cn/problems/profitable-schemes/
 */
public class Code05_ProfitableSchemes {

    public static int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {

        return process1(group, profit, 0, n, minProfit);
    }

    public static int process1(int[] g, int[] p, int i, int r, int s) {
        if (r <= 0) {
            return s <= 0 ? 1 : 0;
        }

        if (i == g.length) {
            return s <= 0 ? 1 : 0;
        }

        int p1 = process1(g, p, i + 1, r, s);
        int p2 = 0;

        if (g[i] <= r) {
            p2 = process1(g, p, i + 1, r - g[i], s - p[i]);
        }

        return p1 + p2;
    }

    /*
    ====================================================================================================================
     */

    public static int mod = 1000000007;

    public static int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int[][][] dp = new int[m][n + 1][minProfit + 1];
        for (int a = 0; a < m; a++) {
            for (int b = 0; b <= n; b++) {
                for (int c = 0; c <= minProfit; c++) {
                    dp[a][b][c] = -1;
                }
            }
        }
        return process2(group, profit, 0, n, minProfit, dp);
    }

    public static int process2(int[] g, int[] p, int i, int r, int s, int[][][] dp) {
        if (r <= 0) {
            return s == 0 ? 1 : 0;
        }
        if (i == g.length) {
            return s == 0 ? 1 : 0;
        }
        if (dp[i][r][s] != -1) {
            return dp[i][r][s];
        }
        int p1 = process2(g, p, i + 1, r, s, dp);
        int p2 = 0;
        if (g[i] <= r) {
            p2 = process2(g, p, i + 1, r - g[i], Math.max(0, s - p[i]), dp);
        }
        int ans = (p1 + p2) % mod;
        dp[i][r][s] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int r = 0; r <= n; r++) {
            dp[r][0] = 1;
        }

        int m = group.length;
        for (int i = m - 1; i >= 0; i--) {
            for (int r = n; r >= 0; r--) {
                for (int s = minProfit; s >= 0; s--) {
                    int p1 = dp[r][s];
                    int p2 = group[i] <= r ? dp[r - group[i]][Math.max(0, s - profit[i])] : 0;
                    dp[r][s] = (p1 + p2) % mod;
                }
            }
        }

        return dp[n][minProfit];
    }
}
