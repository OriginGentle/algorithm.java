package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/7-11:37
 */
public class Problem_0048_RotateImage {

    // 时间复杂度O(N^2)
    // 空间复杂度O(N^2)
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] help = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                help[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = help[i][j];
            }
        }
    }

    /*
    ====================================================================================================================
     */

    // 方法一推出的公式:m[col][n - row - 1] = m[row][col]
    // 对于原数组中的处于 [row][col] 位置的数，经过旋转后要去 [col][n-row-1] 位置
    // 问题在于:如果不进行任何处理，[row][col] 位置的数会直接覆盖 [col][n-row-1] 位置的数!!!
    // tmp1 = m[col][n-row-1]
    // m[col][n-row-1] = m[row][col]
    // 那么原 [col][n-row-1] 位置的数要去哪里呢？
    // 根据公式，很明显是 [n - row - 1][n - col - 1] 位置
    // tmp2 = m[n - row - 1][n - col - 1]
    // m[n - row - 1][n -col -1] = tmp1
    // 接着上述过程继续推：原来 [n - row - 1][n - col - 1]位置的数要去哪个位置呢？
    // 答案是:[n-col-1][row]位置，
    // 原来 [n-col-1][row]位置的数去哪呢？
    // 答案是:[row][col],就回到原点了
    // 总结起来：
    // m[row][col] -> m[col][n - row - 1]
    // m[col][n - row - 1] -> m[n - row - 1][n - col - 1]
    // m[n - row - 1][n - col - 1] -> m[n-col-1][row]
    // m[n-col-1][row] -> m[row][col]
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int row = 0; row < (n >> 1); row++) {
            for (int col = 0; col < ((n + 1) >> 1); col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - col - 1][row];
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = tmp;
            }
        }
    }
}
