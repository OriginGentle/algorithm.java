package com.system.II_basic.day42;

/**
 * @author ycb
 * @date 2021/11/20-15:29
 * @description https://leetcode.com/problems/super-egg-drop
 */
public class Code02_ThrowChessPiecesProblem {

    public static int superEggDrop1(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        return process(nLevel, kChess);
    }

    // rest还剩多少层楼需要去验证
    // k还有多少颗棋子能够使用
    // 一定要验证出最高的不会碎的楼层！但是每次都是坏运气。
    // 返回至少需要扔几次？
    public static int process(int rest, int k) {
        if (rest == 0) {
            return 0;
        }
        if (k == 1) {
            return rest;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i != rest + 1; i++) {
            // 第一次扔的时候，仍在了i层
            // 情况一:棋子碎了，只能i-1层上去试，棋子的数量要 -1
            // 情况二：棋子没碎，那就剩下rest - i层，棋子的数量不变
            min = Math.min(min, Math.max(process(i - 1, k - 1), process(rest - i, k)));
        }
        return min + 1;
    }

    /*
    ====================================================================================================================
     */

    public static int superEggDrop2(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        // dp[i][j] : 从0...i层楼,共有j棵棋子，在最差的情况下扔
        // 找到棋子不会摔碎的最高层数,返回仍的最少次数
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            // 只有1棵棋子，只能一层一层的试
            dp[i][1] = i;
        }
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    /*
    ====================================================================================================================
     */

    public static int superEggDrop3(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        int[][] best = new int[nLevel + 1][kChess + 1];
        for (int j = 1; j != dp[0].length; j++) {
            // 只有一层楼的情况
            dp[1][j] = 1;
            best[1][j] = 1;
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = kChess; j > 1; j--) {
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                int down = best[i - 1][j];
                int up = j == kChess ? i : best[i][j + 1];
                for (int first = down; first <= up; first++) {
                    int cur = Math.max(dp[first - 1][j - 1], dp[i - first][j]);
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = first;
                    }
                }
                dp[i][j] = ans + 1;
                best[i][j] = bestChoose;
            }
        }
        return dp[nLevel][kChess];
    }

    /*
    ====================================================================================================================
     */

    public static int superEggDrop4(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        // dp[i] = 0...i棵棋子，在运气最差的情况往下扔，能试出的楼层最高是多少
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    /*
    ====================================================================================================================
     */

    public static int superEggDrop5(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int bsTimes = log2N(nLevel) + 1;
        if (kChess >= bsTimes) {
            return bsTimes;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int maxN = 500;
        int maxK = 30;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN) + 1;
            int K = (int) (Math.random() * maxK) + 1;
            int ans2 = superEggDrop2(K, N);
            int ans3 = superEggDrop3(K, N);
            int ans4 = superEggDrop4(K, N);
            int ans5 = superEggDrop5(K, N);
            if (ans2 != ans3 || ans4 != ans5 || ans2 != ans4) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
