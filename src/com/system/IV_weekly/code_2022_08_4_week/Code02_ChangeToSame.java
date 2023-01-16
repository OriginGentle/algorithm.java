package com.system.IV_weekly.code_2022_08_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/8/26-08:28
 * @desc 来自美团 8.20笔试
 * 小团生日收到妈妈送的两个一模一样的数列作为礼物！
 * 他很开心的把玩，不过不小心没拿稳将数列摔坏了！
 * 现在他手上的两个数列分别为A和B，长度分别为n和m。
 * 小团很想再次让这两个数列变得一样。他现在能做两种操作：
 * 操作一是将一个选定数列中的某一个数a改成数b，这会花费|b-a|的时间，
 * 操作二是选择一个数列中某个数a，将它从数列中丢掉，花费|a|的时间。
 * 小团想知道，他最少能以多少时间将这两个数列变得再次相同！
 * 输入描述：
 * 第一行两个空格隔开的正整数n和m，分别表示数列A和B的长度。
 * 接下来一行n个整数，分别为A1 A2…An
 * 接下来一行m个整数，分别为B1 B2…Bm
 * 对于所有数据，1 ≤ n,m ≤ 2000， |Ai|,|Bi| ≤ 10000
 * 输出一行一个整数，表示最少花费时间，来使得两个数列相同。
 */
public class Code02_ChangeToSame {

    public static int minCost(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return process2(A, B, 0, 0, dp);
    }

    public static int process1(int[] A, int[] B, int ai, int bi) {
        if (ai == A.length && bi == B.length) {
            return 0;
        }
        if (ai == A.length)
            return B[bi] + process1(A, B, ai, bi + 1);

        if (bi == B.length)
            return A[ai] + process1(A, B, ai + 1, bi);

        int p1 = A[ai] + process1(A, B, ai + 1, bi);
        int p2 = B[bi] + process1(A, B, ai, bi + 1);
        int p3 = Math.abs(A[ai] - B[bi]) + process1(A, B, ai + 1, bi + 1);
        return Math.min(p1, Math.min(p2, p3));
    }

    // 记忆化搜索
    public static int process2(int[] A, int[] B, int ai, int bi, int[][] dp) {
        if (ai == A.length && bi == B.length)
            return 0;
        if (dp[ai][bi] != -1) {
            return dp[ai][bi];
        }
        int ans = 0;
        if (ai == A.length)
            ans = B[bi] + process2(A, B, ai, bi + 1, dp);

        else if (bi == B.length)
            ans = A[ai] + process2(A, B, ai + 1, bi, dp);

        else {
            int p1 = A[ai] + process2(A, B, ai + 1, bi, dp);
            int p2 = B[bi] + process2(A, B, ai, bi + 1, dp);
            int p3 = Math.abs(A[ai] - B[bi]) + process2(A, B, ai + 1, bi + 1, dp);
            ans = Math.min(p1, Math.min(p2, p3));
        }
        dp[ai][bi] = ans;
        return ans;
    }
}

