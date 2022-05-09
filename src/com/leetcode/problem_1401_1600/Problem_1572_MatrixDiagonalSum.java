package com.leetcode.problem_1401_1600;

/**
 * @author ycb
 * @since 2022/3/23-15:02
 */
public class Problem_1572_MatrixDiagonalSum {

    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - i - 1];
        }
        // n & 1 == 0 --》 n 偶数
        // n & 1 == 1 ——》 n 奇数
        return sum - mat[n / 2][n / 2] * (n & 1);
    }

    public static void main(String[] args) {
        int a = 6;
        System.out.println(a & 1);
    }
}
