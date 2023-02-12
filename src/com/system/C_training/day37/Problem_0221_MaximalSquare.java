package com.system.C_training.day37;

/**
 * @author ycb
 * @since 2021/10/25-8:52
 */
public class Problem_0221_MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length, M = matrix[0].length;
        int max = 0;
        // 必须以 i,j 位置作为右下角构成的矩形的边有多长
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 1; j < M; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == '1') {
                    // 依赖左上，左，上位置
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }
}
