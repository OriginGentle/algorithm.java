package com.weekly.code_2022_01_1_week;

/**
 * @author ycb
 * @date 2022/1/8-13:46
 * @description https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
 */
public class Code03_MaximumScoreFromPerformingMultiplicationOperations {

    public static int maximumScore1(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || A.length < B.length) {
            return 0;
        }
        return process1(A, B, 0, A.length - 1);
    }

    public static int process1(int[] A, int[] B, int left, int right) {
        int indexB = left + A.length - right - 1;
        if (indexB == B.length) {
            return 0;
        }
        int p1 = B[indexB] * A[left] + process1(A, B, left + 1, right);
        int p2 = B[indexB] * A[right] + process1(A, B, left, right - 1);
        return Math.max(p1, p2);
    }

    /*
    ====================================================================================================================
     */

    public static int maximumScore2(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || A.length < B.length) {
            return 0;
        }
        int N = A.length;
        int M = B.length;
        int[][] dp = new int[M + 1][M + 1];
        for (int L = M - 1; L >= 0; L--) {
            for (int j = L + 1; j <= M; j++) {
                int R = N - M + j - 1;
                int indexB = L + N - R - 1;
                dp[L][j] = Math.max(A[L] * B[indexB] + dp[L + 1][j], A[R] * B[indexB] + dp[L][j - 1]);
            }
        }
        return dp[0][M];
    }
}
