package com.system.D_weekly.code_2023_03_2_week;

/**
 * @author ycb
 * @date 2023/3/10-13:35
 * @desc 爱丽丝和鲍勃继续他们的石子游戏
 * 许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]
 * 游戏以谁手中的石子最多来决出胜负。
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M
 * 然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设爱丽丝和鲍勃都发挥出最佳水平
 * 返回爱丽丝可以得到的最大数量的石头。
 * 测试链接 : https://leetcode.cn/problems/stone-game-ii/
 */
public class Code01_StoneGameII {

    public static int stoneGameII1(int[] piles) {
        return first1(piles, 0, 1);
    }

    public static int first1(int[] piles, int index, int m) {
        if (index == piles.length) {
            return 0;
        }
        int best = 0, pre = 0;
        for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
            pre += piles[i];
            best = Math.max(best, pre + second1(piles, i + 1, Math.max(m, j)));
        }
        return best;
    }

    public static int second1(int[] piles, int index, int m) {
        if (index == piles.length) {
            return 0;
        }
        int worse = Integer.MAX_VALUE;
        for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
            worse = Math.min(worse, first1(piles, i + 1, Math.max(j, m)));
        }
        return worse;
    }

    /*
    ====================================================================================================================
     */

    public static int stoneGameII2(int[] piles) {
        int[][] f = new int[piles.length][piles.length + 1];
        int[][] s = new int[piles.length][piles.length + 1];
        for (int i = 0; i < piles.length; i++) {
            for (int j = 0; j <= piles.length; j++) {
                f[i][j] = -1;
                s[i][j] = -1;
            }
        }
        return first2(piles, 0, 1, f, s);
    }

    public static int first2(int[] piles, int index, int m, int[][] f, int[][] s) {
        if (index == piles.length) {
            return 0;
        }
        if (f[index][m] != -1) {
            return f[index][m];
        }
        int best = 0, pre = 0;
        for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
            pre += piles[i];
            best = Math.max(best, pre + second2(piles, i + 1, Math.max(m, j), f, s));
        }
        f[index][m] = best;
        return best;
    }

    public static int second2(int[] piles, int index, int m, int[][] f, int[][] s) {
        if (index == piles.length) {
            return 0;
        }
        if (s[index][m] != -1) {
            return s[index][m];
        }
        int worse = Integer.MAX_VALUE;
        for (int i = index, j = 1; i < piles.length && j <= 2 * m; i++, j++) {
            worse = Math.min(worse, first2(piles, i + 1, Math.max(j, m), f, s));
        }
        s[index][m] = worse;
        return worse;
    }

    /*
    ====================================================================================================================
     */

    public static int stoneGameII3(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int m = 1; m <= n; m++) {
                int pre = 0;
                for (int i = index, j = 1; i < n && j <= 2 * m; i++, j++) {
                    pre += piles[i];
                    f[index][m] = Math.max(f[index][m], pre + s[i + 1][Math.min(n, Math.max(j, m))]);
                }

                s[index][m] = Integer.MAX_VALUE;
                for (int i = index, j = 1; i < n && j <= 2 * m; i++, j++) {
                    s[index][m] = Math.min(s[index][m], f[i + 1][Math.min(n, Math.max(j, m))]);
                }
            }
        }
        return f[0][1];
    }

    /*
    ====================================================================================================================
     */

    public static int stoneGameII4(int[] piles) {
        int n = piles.length, sum = 0;
        int[][] dp = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sum += piles[i];
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    dp[i][m] = sum;
                } else {
                    int nextMin = Integer.MAX_VALUE;
                    for (int j = 1; j <= 2 * m; j++) {
                        nextMin = Math.min(nextMin, dp[i + j][Math.max(j, m)]);
                    }

                    dp[i][m] = sum - nextMin;
                }
            }
        }
        return dp[0][1];
    }

}
