package com.leetcode;

/**
 * @author ycb
 * @since 2022/2/17-8:21
 */
public class Problem_0688_KnightProbabilityInChessboard {

    public static double knightProbability1(int n, int k, int row, int column) {
        return (double) process(row, column, k, n) / Math.pow(8, k);
    }

    public static long process(int row, int col, int rest, int n) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        long p1 = process(row - 1, col - 2, rest - 1, n);
        long p2 = process(row - 1, col + 2, rest - 1, n);
        long p3 = process(row - 2, col - 1, rest - 1, n);
        long p4 = process(row - 2, col + 1, rest - 1, n);
        long p5 = process(row + 1, col - 2, rest - 1, n);
        long p6 = process(row + 1, col + 2, rest - 1, n);
        long p7 = process(row + 2, col + 1, rest - 1, n);
        long p8 = process(row + 2, col - 1, rest - 1, n);
        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }

    /*
    ====================================================================================================================
     */

    public static double knightProbability2(int n, int k, int x, int y) {
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    dp[row][col][rest] += pick(dp, n, row - 1, col - 2, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row - 1, col + 2, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row - 2, col - 1, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row - 2, col + 1, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row + 1, col - 2, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row + 1, col + 2, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row + 2, col + 1, rest - 1);
                    dp[row][col][rest] += pick(dp, n, row + 2, col - 1, rest - 1);
                }
            }
        }
        return dp[x][y][k] / Math.pow(8, k);
    }

    public static double pick(double[][][] dp, int N, int row, int col, int rest) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return 0;
        }
        return dp[row][col][rest];
    }

    /*
    ====================================================================================================================
     */

    int[][] next = new int[][]{{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1}};

    public double knightProbability3(int n, int k, int row, int column) {
        double[][][] f = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j][0] = 1;
            }
        }
        for (int p = 1; p <= k; p++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] d : next) {
                        int nx = i + d[0], ny = j + d[1];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        f[i][j][p] += f[nx][ny][p - 1] / 8;
                    }
                }
            }
        }
        return f[row][column][k];
    }
}
