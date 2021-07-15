package com.training.day01;

/**
 * @Author ycb
 * @Date 2021/7/13-13:33
 * @Description 给定一个二维数组matrix，
 * 你可以从任何位置出发，走向上下左右四个方向
 * 返回能走出来的最长的递增链长度
 */
public class Code05_LongestIncreasingPath {

    // 暴力递归
    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 每个位置都去尝试，看最长是多少
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    // 从m[i][j]开始走，返回走出去的最长递增链
    public static int process1(int[][] m, int i, int j) {
        int up = i > 0 && m[i - 1][j] > m[i][j] ? process1(m, i - 1, j) : 0;
        int down = i < (m.length - 1) && m[i + 1][j] > m[i][j] ? process1(m, i + 1, j) : 0;
        int left = j > 0 && m[i][j - 1] > m[i][j] ? process1(m, i, j - 1) : 0;
        int right = j < (m[0].length - 1) && m[i][j + 1] > m[i][j] ? process1(m, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    /*
    ====================================================================================================================
     */

    // 记忆化搜索
    public static int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    // 从m[i][j]开始走，返回走出去的最长递增链
    public static int process2(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int ans = 0;
        int up = i > 0 && m[i - 1][j] > m[i][j] ? process2(m, i - 1, j, dp) : 0;
        int down = i < (m.length - 1) && m[i + 1][j] > m[i][j] ? process2(m, i + 1, j, dp) : 0;
        int left = j > 0 && m[i][j - 1] > m[i][j] ? process2(m, i, j - 1, dp) : 0;
        int right = j < (m[0].length - 1) && m[i][j + 1] > m[i][j] ? process2(m, i, j + 1, dp) : 0;
        ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

}
